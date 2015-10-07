package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.interactioners.LightInterActioner;
import sb.jsonapi.ENetType;
import sb.agents.DefaultAgent;

@SuppressWarnings("serial")
public class LightSensors extends TickerBehaviour {
	private LightInterActioner	_light;
	private DefaultAgent 		_defaultAgent;
	private int 				_lightState;

	public LightSensors(Agent a, LightInterActioner lightToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_light = lightToWatch;
		_lightState = _light.getValue();
	}

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
