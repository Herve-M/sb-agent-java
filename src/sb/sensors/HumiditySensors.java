/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.HumidityActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

/**
 * The Class HumiditySensors.
 */
@SuppressWarnings("serial")
public class HumiditySensors extends TickerBehaviour {
	
	/** The _humidity. */
	private HumidityActioner 	_humidity;
	
	/** The _default agent. */
	private DefaultAgent 		_defaultAgent;
	
	/** The _humidity state. */
	private int 				_humidityState;

	/**
	 * Instantiates a new humidity sensors.
	 *
	 * @param a the a
	 * @param humidityToWatch the humidity to watch
	 */
	public HumiditySensors(Agent a, HumidityActioner humidityToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_humidity = humidityToWatch;
		_humidityState = _humidity.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_humidity.getState()){
			if(_humidity.getValue() != _humidityState){
				_humidityState = _humidity.getValue();
				_defaultAgent.sendInform(ENetType.HUMIDITY, String.valueOf(_humidityState));
				System.out.println("[SENSORS] HumiditySensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.HUMIDITY);
		}	
	}
}
