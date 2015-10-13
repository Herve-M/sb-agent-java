package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;
import sb.interactioners.AirConditionerInterActioner;
import sb.jsonapi.JSSMAState;
import sb.jsonapi.MSJson;

public class FSMAirConditionerGetter extends OneShotBehaviour {
	private AirConditionerInterActioner 	_airConditioner;
	private String 							_name;
	
	public FSMAirConditionerGetter(String airCondName) {
		_name = airCondName;
	}
	
	@Override
	public void action() {
		_airConditioner = new AirConditionerInterActioner(_name);
	}
	
	@Override
	public int onEnd() {
		boolean state = _airConditioner.getState();
		System.out.println("FSM : AC is "+state);
		if(state)
			return 1;
		else
			return 0;
	}
}
