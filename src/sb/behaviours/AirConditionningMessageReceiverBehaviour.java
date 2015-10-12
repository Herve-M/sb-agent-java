/**
 * 
 */
package sb.behaviours;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sb.interactioners.AirConditionerInterActioner;
import sb.messaging.template.AirConditionerTemplate;

/**
 * @author Psyko
 *
 */
public class AirConditionningMessageReceiverBehaviour extends DefaultMessageReceiverBehaviour{

	private AirConditionerInterActioner _airConditionner;
	
	/**
	 * @param a
	 */
	public AirConditionningMessageReceiverBehaviour(Agent a, AirConditionerInterActioner toWatch) {
		super(a);
		this._airConditionner = toWatch;
		this._template = new MessageTemplate(new AirConditionerTemplate());
	}
	
	/* (non-Javadoc)
	 * @see sb.behaviours.DefaultMessageReceiverBehaviour#action()
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

	/**
	 * @param msg
	 */
	private void treatMessage(ACLMessage msg) {
		
		switch (msg.getPerformative()) {
			case ACLMessage.REQUEST:
				SolveRequest(msg);
				break;
	
			default:
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
				myAgent.send(reply);
				break;
		}
	}

	/**
	 * Solve request.
	 *
	 * @param msg the msg
	 */
	private void SolveRequest(ACLMessage msg) {
		
		if (msg.getOntology().contains("heating")) {
			if (msg.getContent().contains("ON") || msg.getContent().contains("OFF")) { // call for starting the airconditioner
				ACLMessage reply = msg.createReply();
				reply.setPerformative(ACLMessage.INFORM_IF);
				reply.setOntology("heating");
				reply.setContent(String.valueOf(_airConditionner.getState()));
				myAgent.send(reply);
			}
		}
	}

}
