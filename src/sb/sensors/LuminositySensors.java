/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.LuminosityActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

/**
 * The Class LuminositySensors.
 */
public class LuminositySensors extends TickerBehaviour {
	
	/** The _luminosity. */
	private LuminosityActioner 	_luminosity;
	
	/** The _default agent. */
	private DefaultAgent 		_defaultAgent;
	
	/** The _luminosity state. */
	private int 				_luminosityState;

	/**
	 * Instantiates a new luminosity sensors.
	 *
	 * @param a the a
	 * @param luminosityToWatch the luminosity to watch
	 */
	public LuminositySensors(Agent a, LuminosityActioner luminosityToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_luminosity = luminosityToWatch;
		_luminosityState = _luminosity.getValue();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		if(_luminosity.getState()){
			if(_luminosity.getValue() != _luminosityState){
				_luminosityState = _luminosity.getValue();
				_defaultAgent.sendInform(ENetType.LUMINOSITY, String.valueOf(_luminosityState));
				System.out.println("LuminositySensors state changed");
			} 
		} else {
			_defaultAgent.sendFailure(ENetType.LUMINOSITY);
		}
	}

}
