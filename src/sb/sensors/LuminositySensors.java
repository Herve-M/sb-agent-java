package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.LuminosityActioner;
import sb.agents.DefaultAgent;

public class LuminositySensors extends TickerBehaviour {
	private LuminosityActioner 	_luminosity;
	private DefaultAgent 		_defaultAgent;
	private int 				_luminosityState;

	public LuminositySensors(Agent a, LuminosityActioner luminosityToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_luminosity = luminosityToWatch;
		_luminosityState = _luminosity.getValue();
	}

	@Override
	protected void onTick() {
		if(_luminosity.getState()){
			if(_luminosity.getValue() != _luminosityState){
				//TODO sendMSG
			}
		}
	}

}
