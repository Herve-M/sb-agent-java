/**
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update: 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.agents.DefaultAgent;
import sb.interactioners.AirConditionerInterActioner;
import sb.jsonapi.ENetType;

/**
 *
 */
public class AirConditionerSensors extends TickerBehaviour {

	private AirConditionerInterActioner _airConditioner;
	
	private DefaultAgent _defaultAgent;
	
	private int _airConditionerState;
	
	/**
	 * 
	 */
	public AirConditionerSensors(Agent a, AirConditionerInterActioner airConditionerToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_airConditioner = airConditionerToWatch;
		_airConditionerState = _airConditioner.getValue();
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if (_airConditioner.getState()) {
			if (_airConditioner.getValue() != _airConditionerState) {
				_airConditionerState = _airConditioner.getValue();
				_defaultAgent.sendInform(ENetType.HEATING, String.valueOf(_airConditionerState));
			}
		} else {
			_defaultAgent.sendFailure(ENetType.AIRCONDITIONER);
		}
	}

}
