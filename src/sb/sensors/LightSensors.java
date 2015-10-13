/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.interactioners.LightInterActioner;
import sb.jsonapi.ENetType;
import sb.agents.DefaultAgent;

/**
 * The Class LightSensors.
 */
@SuppressWarnings("serial")
public class LightSensors extends TickerBehaviour {
	
	/** The _light. */
	private LightInterActioner	_light;
	
	/** The _default agent. */
	private DefaultAgent 		_defaultAgent;
	
	/** The _light state. */
	private int 				_lightState;

	/**
	 * Instantiates a new light sensors.
	 *
	 * @param a the a
	 * @param lightToWatch the light to watch
	 */
	public LightSensors(Agent a, LightInterActioner lightToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_light = lightToWatch;
		_lightState = _light.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_light.getState()){
			if(_light.getValue() != _lightState){
				_lightState = _light.getValue();
				_defaultAgent.sendInform(ENetType.LIGHT, String.valueOf(_lightState));
				System.out.println("LightSensors state changed");
			} 
		} else {
			_defaultAgent.sendFailure(ENetType.LIGHT);
		}
	}

}
