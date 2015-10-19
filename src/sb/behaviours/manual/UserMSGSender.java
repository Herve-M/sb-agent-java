package sb.behaviours.manual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import sb.helpers.ClassificationHelper;
import sb.helpers.EAction;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.helpers.EUserAction;
import sb.sensors.AirConditionerMSGSender;
import sb.sensors.HeatingMSGSender;

public class UserMSGSender extends OneShotBehaviour {
	/** The receivers. */
	private List<AID> 	_receivers = new ArrayList<>();
	private EUserAction _action;
	private int 		_roomId;

	public UserMSGSender(Agent a, EUserAction userAction, int roomId) {
		super(a);
		_action = userAction;
		_roomId = roomId;
		
		DFAgentDescription researchTemplate = new DFAgentDescription();
		// Agent Description
		ServiceDescription agentDescription = new ServiceDescription();
		agentDescription.setName("CLASS");
		agentDescription.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.USER, ETypeHelper.NONE));
		// Room Description
		ServiceDescription roomDescription  = new ServiceDescription();
		roomDescription.setName("ROOMID");
		roomDescription.setType(String.valueOf(_roomId));
		
		researchTemplate.addServices(agentDescription);
		researchTemplate.addServices(roomDescription);
		
		try {			
			DFAgentDescription[] result = DFService.search(myAgent, researchTemplate);
			if(result.length == 0)
				System.out.println("USER-MSG-SENDER Err : no agent found");
			for (DFAgentDescription dfAgentDescription : result) {
					_receivers.add(dfAgentDescription.getName());
			}			
		}
		catch (FIPAException fe) {
			System.err.println("USER-MSG-SENDER Err : "+fe.getMessage());
		}
	}	
	
	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		for (AID agent : _receivers) {
			msg.addReceiver(agent);
		}
		msg.setLanguage("English");
		msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msg.setContent(_action.toString());
		msg.setReplyByDate(new Date());
		
		myAgent.addBehaviour(new AchieveREInitiator(myAgent, msg){	
			
			@Override
			protected void handleAgree(ACLMessage agree) {
				System.out.println("User "+agree.getSender().getName()+" agreed the action");
				String actionStr = agree.getContent();
				EUserAction action = EUserAction.valueOf(actionStr);
				switch (action) {
				case HTON:
					myAgent.addBehaviour(new HeatingMSGSender(myAgent, EAction.ON, _roomId));
					break;
				case HTOFF:
					myAgent.addBehaviour(new HeatingMSGSender(myAgent, EAction.OFF, _roomId));
					break;
				case ACON:
					myAgent.addBehaviour(new AirConditionerMSGSender(myAgent, EAction.ON, _roomId));
					break;
				default:
					System.err.println("User send unknow action");
					ACLMessage reply = agree.createReply();
					reply.setPerformative(ACLMessage.UNKNOWN);
					myAgent.send(reply);
					break;
				}
			}
			
			@Override
			protected void handleRefuse(ACLMessage refuse) {
				System.err.println("Error REQUEST was rerfused by "+refuse.getSender().getName());
			}
			
			@Override
			protected void handleFailure(ACLMessage failure) {
				System.err.println("Error REQUEST on a shutdowned agent : "+failure.getSender().getName());
			}
			
			@Override
			protected void handleNotUnderstood(ACLMessage notUnderstood) {
				System.err.println("Error REQUEST was not understood by agent "+notUnderstood.getSender().getName());
			}
			
			@Override
			public int onEnd() {
				reset();
				return super.onEnd();
			}
		});
	}
	
	@Override
	public int onEnd() {
		reset();
		return super.onEnd();
	}
	
}
