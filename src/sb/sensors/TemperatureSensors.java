package sb.sensors;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import sb.actioners.TemperatureActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

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
				_temperatureState = _temperature.getValue();
				_defaultAgent.sendInform(ENetType.TEMPERATURE, String.valueOf(_temperatureState));
				System.out.println("ShutterSensors state changed");
			}
		} else {
			_defaultAgent.sendFailure(ENetType.TEMPERATURE);
		}
	}
}
