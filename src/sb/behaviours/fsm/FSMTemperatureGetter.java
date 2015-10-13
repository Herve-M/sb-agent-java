package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.JSTemperature;
import sb.jsonapi.MSJson;

public class FSMTemperatureGetter extends OneShotBehaviour {
	private JSTemperature _temperature;
	
	public FSMTemperatureGetter(int roomId) {
		_temperature = MSJson.getTemperatureByRoom(roomId);
	}
	
	@Override
	public void action() {
		_temperature = MSJson.getTemperatureByRoom(_temperature.Id);
	}
	
	@Override
	public int onEnd() {
		if(_temperature.Average > _temperature.Max)
			return 1;
		else if(_temperature.Average < _temperature.Max && _temperature.Average > _temperature.Min)
			return 0;
		else
			return -1;
	}

}
