/**
 * 
 */
package sb.behaviours;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sb.agents.DefaultAgent;
import sb.interactioners.HeatingInterActioner;
import sb.messaging.template.HeatingTemplate;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

/**
 * @author Psyko
 *
 */
public class HeaterMessageReceiverBehaviour extends DefaultMessageReceiverBehaviour {

	/** The _heating. */
	private HeatingInterActioner	_heating;
	
	/**
	 * Instantiates a new heater message receiver behaviour.
	 */
	public HeaterMessageReceiverBehaviour(Agent a, HeatingInterActioner heatingToWatch) {
		
		super(a);
		this._heating = heatingToWatch;
		
		ACLMessage msg = myAgent.receive(new MessageTemplate(new HeatingTemplate()));
		
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
		if () {
			
		}
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		
	}
}
