package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.HumidityActioner;
import sb.agents.DefaultAgent;

@SuppressWarnings("serial")
public class HumiditySensors extends TickerBehaviour {
	private HumidityActioner 	_humidity;
	private DefaultAgent 		_defaultAgent;
	private int 				_humidityState;

	public HumiditySensors(Agent a, HumidityActioner humidityToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_humidity = humidityToWatch;
		_humidityState = _humidity.getValue();
	}

	@Override
	protected void onTick() {
		if(_humidity.getState()){
			if(_humidity.getValue() != _humidityState){
				//TODO sendMSG
			}
		}		
	}
}