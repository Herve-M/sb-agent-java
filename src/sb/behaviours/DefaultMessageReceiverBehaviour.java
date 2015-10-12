/**
 * 
 */
package sb.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import sb.agents.DefaultAgent;

/**
 * @author Psyko
 *
 */
public class DefaultMessageReceiverBehaviour extends CyclicBehaviour {

	
	/** The _default agent. */
	private DefaultAgent 			_defaultAgent;
	
	/**
	 * 
	 */
	public DefaultMessageReceiverBehaviour(Agent a) {
		super();
		this._defaultAgent = (DefaultAgent) a;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		ACLMessage receive = myAgent.receive();
		
		if (receive != null) {
			
		} else {
			block();
		}
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#onEnd()
	 */
	@Override
	public int onEnd() {
		return super.onEnd();
	}
	
}
