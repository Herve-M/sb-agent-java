package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.IOActioner;
import sb.agents.DefaultAgent;
import sb.jsonapi.ENetType;

@SuppressWarnings("serial")
public class IOSensors extends TickerBehaviour{

	private IOActioner _ioActioner;
	private DefaultAgent _defaultAgent;
	private int _ioState;
	
	public IOSensors(Agent a, IOActioner IOToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_ioActioner = IOToWatch;
		_ioState = _ioActioner.getValue();
	}
	
	@Override
	protected void onTick() {
		if (_ioActioner.getState()) {
			if (_ioActioner.getValue() != _ioState) {
				_ioState = _ioActioner.getValue();
				_defaultAgent.sendInform(ENetType.IO, String.valueOf(_ioState));
			}
		} else {
			_defaultAgent.sendFailure(ENetType.IO);
		}
	}
	
}
