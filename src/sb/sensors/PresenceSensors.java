/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.PresenceActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

/**
 * The Class PresenceSensors.
 */
@SuppressWarnings("serial")
public class PresenceSensors extends TickerBehaviour {
	
	/** The _presence. */
	private PresenceActioner	_presence;
	
	/** The _default agent. */
	private DefaultAgent 		_defaultAgent;
	
	/** The _presence state. */
	private int 				_presenceState;

	/**
	 * Instantiates a new presence sensors.
	 *
	 * @param a the a
	 * @param presenceToWatch the presence to watch
	 */
	public PresenceSensors(Agent a, PresenceActioner presenceToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_presence = presenceToWatch;
		_presenceState = _presence.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_presence.getState()){
			if(_presence.getValue() != _presenceState){
				_presenceState = _presence.getValue();
				_defaultAgent.sendInform(ENetType.PRESENCE, String.valueOf(_presenceState));
				System.out.println("[SENSORS] PresenceSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.PRESENCE);
		}
	}

}
