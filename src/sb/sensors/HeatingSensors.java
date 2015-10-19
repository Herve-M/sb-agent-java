/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.agents.DefaultAgent;
import sb.interactioners.HeatingInterActioner;
import sb.jsonapi.ENetType;

/**
 * The Class HeatingSensors.
 * Return a msg every X secs if value of Sensors change.
 */
@SuppressWarnings("serial")
public class HeatingSensors extends TickerBehaviour {
	
	/** The _heating. */
	private HeatingInterActioner	_heating;
	
	/** The _default agent. */
	private DefaultAgent 			_defaultAgent;
	
	/** The _heating state. */
	private int 					_heatingState;

	/**
	 * Instantiates a new heating sensors.
	 *
	 * @param a the a
	 * @param heatingToWatch the heating to watch
	 */
	public HeatingSensors(Agent a, HeatingInterActioner heatingToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_heating = heatingToWatch;
		_heatingState = _heating.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_heating.getState()){
			if(_heating.getValue() != _heatingState){
				_heatingState = _heating.getValue();
				_defaultAgent.sendInform(ENetType.HEATING, String.valueOf(_heatingState));
				System.out.println("[SENSORS] HeatingSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.HEATING);
		}
	}

}
