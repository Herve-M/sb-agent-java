package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.interactioners.HeatingInterActioner;
import sb.jsonapi.ENetType;
import sb.agents.DefaultAgent;

@SuppressWarnings("serial")
public class HeatingSensors extends TickerBehaviour {
	private HeatingInterActioner	_heating;
	private DefaultAgent 			_defaultAgent;
	private int 					_heatingState;

	public HeatingSensors(Agent a, HeatingInterActioner heatingToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_heating = heatingToWatch;
		_heatingState = _heating.getValue();
	}

	@Override
	protected void onTick() {
		if(_heating.getState()){
			if(_heating.getValue() != _heatingState){
				_heatingState = _heating.getValue();
				_defaultAgent.sendInform(ENetType.HEATING, String.valueOf(_heatingState));
			}
		} else {
			_defaultAgent.sendFailure(ENetType.HEATING);
		}
	}

}
