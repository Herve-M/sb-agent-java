package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import sb.agents.DefaultAgent;
import sb.equipment.EAction;
import sb.equipment.HeatingEquipment;
import sb.interactioners.HeatingInterActioner;

/**
 * FIPA RE Responser || Can be only added to ListenerAgent
 * Respond to REQUEST :
 * > ON / OFF
 * > P1 / M1
 * @author Hervé
 *
 */
public class HeatingMSGResponder extends OneShotBehaviour { //OneShot register the behaviour to Agent
	private MessageTemplate _template;
	
	/** The _default agent. */
	private DefaultAgent 			_defaultAgent;
	
	public HeatingMSGResponder(Agent a) {
		super(a);
		_defaultAgent = (DefaultAgent) myAgent;
		System.out.println(_defaultAgent.targetedObject);
		_template = MessageTemplate.and(
		  		MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST) );
	}

	@Override
	public void action() {
		myAgent.addBehaviour(new AchieveREResponder(myAgent, _template){
			private HeatingInterActioner	_heating = new HeatingInterActioner(_defaultAgent.targetedObject);
			private EAction					_action;
			private int 					_oldValue;
			private boolean 				_oldState;
			
			@Override
			protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
				String msgContent = request.getContent();
				_action = EAction.valueOf(msgContent);
								
				if(_heating.getState() == false){
					if(_action != EAction.ON) {
						throw new RefuseException("Tried to action on halted equipment");
					}
				}
				
				switch (_action) {
				case ON:{
					_oldState = false;
					myAgent.addBehaviour(new HeatingEquipment(myAgent, 1));
					break;
				}			
				case OFF:{
					_oldState = true;
					myAgent.addBehaviour(new HeatingEquipment(myAgent, 0));
					break;
				}	
				case P1:{
					_oldValue = _heating.getValue();
					myAgent.addBehaviour(new HeatingEquipment(myAgent, _oldValue+1));
					break;
				}
				case M1:{
					_oldValue = _heating.getValue();
					myAgent.addBehaviour(new HeatingEquipment(myAgent, _heating.getValue()-1)); //TODO : x < 0 ?
					break;
				}
				default:
					throw new NotUnderstoodException("Tried an unknow action on equipment");
				}
				
				ACLMessage agree = request.createReply();
				agree.setPerformative(ACLMessage.AGREE);
				return agree;
			}
			
			@Override
			protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response)
					throws FailureException {
				
				switch (_action) {
				case ON:{
					if(_oldState == _heating.getState())
						throw new FailureException("Heater doesn't start !!");
					break;
				}			
				case OFF:{
					if(_oldState == _heating.getState())
						throw new FailureException("Heater doesn't stop !!");
					break;
				}	
				case P1:{
					if(_oldValue == _heating.getValue())
						throw new FailureException("Heater can't be increased !!");
					break;
				}
				case M1:{
					if(_oldValue == _heating.getValue())
						throw new FailureException("Heater can't be decreased !!");
					break;
				}
				default:
					throw new FailureException("Unknow or No effect on equipment side.");
				}
				
				ACLMessage inform = request.createReply();
				inform.setPerformative(ACLMessage.INFORM);
				return inform;
			}
		});		
	}
}
