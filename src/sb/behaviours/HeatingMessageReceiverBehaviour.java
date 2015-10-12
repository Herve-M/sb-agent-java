/**
 * 
 */
package sb.behaviours;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sb.agents.DefaultAgent;
import sb.interactioners.HeatingInterActioner;
import sb.messaging.template.HeatingTemplate;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;

/**
 * @author Psyko
 *
 */
public class HeatingMessageReceiverBehaviour extends DefaultMessageReceiverBehaviour {

	/** The _heating. */
	private HeatingInterActioner	_heating;
	
	private boolean _prepareHeating;
	
	/**
	 * Instantiates a new heater message receiver behaviour.
	 *
	 * @param a the agent
	 * @param heatingToWatch the heating to watch
	 */
	public HeatingMessageReceiverBehaviour(Agent a, HeatingInterActioner heatingToWatch) {
		
		super(a);
		this._heating = heatingToWatch;
		this._template = new MessageTemplate(new HeatingTemplate());
		_prepareHeating = false;
	}
	
	/**
	 * Treat message.
	 *
	 * @param msg the msg
	 */
	private void treatMessage(ACLMessage msg) {
		
		switch (msg.getPerformative()) {
		case ACLMessage.REQUEST:
			SolveRequest(msg);
			break;
		case ACLMessage.INFORM_IF:
			SolveInformIf(msg);
			break;

		default:
			ACLMessage reply = msg.createReply();
			reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
			myAgent.send(reply);
			break;
		}
	}

	/**
	 * Solve confirm.
	 *
	 * @param msg the msg
	 */
	private void SolveInformIf(ACLMessage msg) {
		if (msg.getContent().contains("OFF") && _prepareHeating) {
			_heating.setState(true);
		}
		
		_prepareHeating = false;
	}

	/**
	 * Solve request.
	 *
	 * @param msg the msg
	 */
	private void SolveRequest(ACLMessage msg) {
		
		if (msg.getContent().contains("ON")) { // call for starting the heating
			if (!_heating.getState()) {
				_prepareHeating = true;
				ACLMessage newMessage = new ACLMessage(ACLMessage.REQUEST);
				newMessage.setOntology("airconditioning");
				newMessage.setContent("ON");
				myAgent.send(newMessage);
			}
		} else
		if (msg.getContent().contains("OFF")) {// call for stopping the heating
			if (_heating.getState()) {
				_heating.setState(false);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {

		ACLMessage msg = myAgent.receive(_template);
		
		if (msg != null) {
			treatMessage(msg);
		} else {
			block();
		}
	}
}
