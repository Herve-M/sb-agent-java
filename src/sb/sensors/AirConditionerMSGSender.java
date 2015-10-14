package sb.sensors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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

public class AirConditionerMSGSender extends OneShotBehaviour {
	
	/** The receivers. */
	private List<AID> 	_receivers = new ArrayList<>();
	private EAction		_action;
	
	public AirConditionerMSGSender(Agent a, EAction htAction, int roomId) {
		super(a);
		_action = htAction;
		
		DFAgentDescription researchTemplate = new DFAgentDescription();
		// Agent Description
		ServiceDescription agentDescription = new ServiceDescription();
		agentDescription.setName("CLASS");
		agentDescription.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.ACTIONER, ETypeHelper.AIRCONDITIONER));
		// Room Description
		ServiceDescription roomDescription  = new ServiceDescription();
		roomDescription.setName("ROOMID");
		roomDescription.setType(String.valueOf(roomId));
		
		researchTemplate.addServices(agentDescription);
		researchTemplate.addServices(roomDescription);
		
		try {			
			DFAgentDescription[] result = DFService.search(myAgent, researchTemplate);
			if(result.length == 0)
				System.err.println("AC-MSG-SENDER Err : no agent found");
			for (DFAgentDescription dfAgentDescription : result) {
					_receivers.add(dfAgentDescription.getName());
			}			
		}
		catch (FIPAException fe) {
			System.err.println("AC-MSG-SENDER Err : "+fe.getMessage());
		}
	}

	/**
	 * Gestion des erreurs
	 */
	@SuppressWarnings("serial")
	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		for (AID agent : _receivers) {
			msg.addReceiver(agent);
		}
        msg.setReplyByDate(new Date(System.currentTimeMillis() + 15000)); //Max reply in 10 secs
		msg.setLanguage("English");
		//msg.setOntology(ENetType.HEATING+"-REQUEST"); //No need use protocol instead
		msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
		msg.setContent(_action.toString());
		
		myAgent.addBehaviour(new AchieveREInitiator(myAgent, msg){	
			
			@Override
			protected void handleAgree(ACLMessage agree) {
				System.out.println("Agent "+agree.getSender().getName()+" agreed to work on action");
			}
			
			@Override
			protected void handleInform(ACLMessage inform) {
				System.out.println("Agent "+inform.getSender().getName()+" successfully performed the requested action");
			}
			
			@Override
			protected void handleRefuse(ACLMessage refuse) {
				System.err.println("Error REQUEST was rerfused by "+refuse.getSender().getName()+" : "+refuse.getContent());
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
			protected void handleAllResultNotifications(Vector resultNotifications) {
				if (resultNotifications.size() < _receivers.size()) {
					System.err.println("Timeout expired: missing "+(_receivers.size() - resultNotifications.size())+" responses");
                }
			}
		});
	}

}
