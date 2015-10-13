package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;
import sb.interactioners.HeatingInterActioner;
import sb.jsonapi.JSSMAState;
import sb.jsonapi.MSJson;

public class FSMHeatingGetter extends OneShotBehaviour {
	private HeatingInterActioner 	_heating;
	private String 					_name;
	
	public FSMHeatingGetter(String heatingName) {
		_name = heatingName;
	}
	
	@Override
	public void action() {
		_heating = new HeatingInterActioner(_name);
	}
	
	@Override
	public int onEnd() {
		boolean state = _heating.getState();
		System.out.println("FSM : Heating is "+state);
		if(state)
			return 1;
		else
			return 0;
	}
}
