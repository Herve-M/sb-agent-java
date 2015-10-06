package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.TemperatureActioner;
import sb.agents.DefaultAgent;

@SuppressWarnings("serial")
public class TemperatureSensors extends TickerBehaviour {
	
	private TemperatureActioner _temperature;
	private DefaultAgent 		_defaultAgent;
	private int 				_temperatureState;

	public TemperatureSensors(Agent a, TemperatureActioner temperatureToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_temperature = temperatureToWatch;
		_temperatureState = _temperature.getValue();
	}

	@Override
	protected void onTick() {
		if(_temperature.getState()){
			if(_temperature.getValue() != _temperatureState){
				//TODO sendMSG
			}
		}		
	}
}
