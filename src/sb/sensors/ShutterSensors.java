/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.interactioners.ShutterInterActioner;
import sb.jsonapi.ENetType;
import sb.agents.DefaultAgent;

/**
 * The Class ShutterSensors.
 */
@SuppressWarnings("serial")
public class ShutterSensors extends TickerBehaviour {
	
	/** The _shutter. */
	private ShutterInterActioner	_shutter;
	
	/** The _default agent. */
	private DefaultAgent 			_defaultAgent;
	
	/** The _shutter state. */
	private int 					_shutterState;

	/**
	 * Instantiates a new shutter sensors.
	 *
	 * @param a the a
	 * @param shutterToWatch the shutter to watch
	 */
	public ShutterSensors(Agent a, ShutterInterActioner shutterToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_shutter = shutterToWatch;
		_shutterState = _shutter.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_shutter.getState()){
			if(_shutter.getValue() != _shutterState){
				_shutterState = _shutter.getValue();
				_defaultAgent.sendInform(ENetType.SHUTTER, String.valueOf(_shutterState));
				System.out.println("ShutterSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.SHUTTER);
		}

	}

}
