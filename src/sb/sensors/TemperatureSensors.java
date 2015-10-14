/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.TemperatureActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

/**
 * The Class TemperatureSensors.
 */
@SuppressWarnings("serial")
public class TemperatureSensors extends TickerBehaviour {
	
	/** The _temperature. */
	private TemperatureActioner _temperature;
	
	/** The _default agent. */
	private DefaultAgent 		_defaultAgent;
	
	/** The _temperature state. */
	private int 				_temperatureState;

	/**
	 * Instantiates a new temperature sensors.
	 *
	 * @param a the a
	 * @param temperatureToWatch the temperature to watch
	 */
	public TemperatureSensors(Agent a, TemperatureActioner temperatureToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_temperature = temperatureToWatch;
		_temperatureState = _temperature.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_temperature.getState()){
			if(_temperature.getValue() != _temperatureState){
				_temperatureState = _temperature.getValue();
				_defaultAgent.sendInform(ENetType.TEMPERATURE, String.valueOf(_temperatureState));
				System.out.println("[SENSORS] TemperatureSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.TEMPERATURE);
		}
	}
}
