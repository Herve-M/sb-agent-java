/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.IOActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

/**
 * The Class IOSensors.
 */
@SuppressWarnings("serial")
public class IOSensors extends TickerBehaviour{

	/** The _io actioner. */
	private IOActioner _ioActioner;
	
	/** The _default agent. */
	private DefaultAgent _defaultAgent;
	
	/** The _io state. */
	private int _ioState;
	
	/**
	 * Instantiates a new IO sensors.
	 *
	 * @param a the a
	 * @param IOToWatch the IO to watch
	 */
	public IOSensors(Agent a, IOActioner IOToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_ioActioner = IOToWatch;
		_ioState = _ioActioner.getValue();
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if (_ioActioner.getState()) {
			if (_ioActioner.getValue() != _ioState) {
				_ioState = _ioActioner.getValue();
				_defaultAgent.sendInform(ENetType.IO, String.valueOf(_ioState));
				System.out.println("ShutterSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.IO);
		}
	}
	
}
