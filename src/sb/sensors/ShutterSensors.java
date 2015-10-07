package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.interactioners.ShutterInterActioner;
import sb.jsonapi.ENetType;
import sb.agents.DefaultAgent;

@SuppressWarnings("serial")
public class ShutterSensors extends TickerBehaviour {
	private ShutterInterActioner	_shutter;
	private DefaultAgent 			_defaultAgent;
	private int 					_shutterState;

	public ShutterSensors(Agent a, ShutterInterActioner shutterToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_shutter = shutterToWatch;
		_shutterState = _shutter.getValue();
	}

	@Override
	protected void onTick() {
		if(_shutter.getState()){
			if(_shutter.getValue() != _shutterState){
				_shutterState = _shutter.getValue();
				_defaultAgent.sendInform(ENetType.SHUTTER, String.valueOf(_shutterState));
			} else {
				_defaultAgent.sendFailure(ENetType.SHUTTER);
			}
		}

	}

}
