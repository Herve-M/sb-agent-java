/**
 * 
 */
package sb.behaviours;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.MessageTemplate;
import sb.agents.DefaultAgent;

/**
 * @author Psyko
 *
 */
public class DefaultMessageReceiverBehaviour extends CyclicBehaviour {

	
	/** The _default agent. */
	private DefaultAgent 			_defaultAgent;
	
	/** The template of the researched message (REQUEST) */
	protected MessageTemplate 		_template;
	
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
	public void action() {}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#onEnd()
	 */
	@Override
	public int onEnd() {
		return super.onEnd();
	}
	
}
