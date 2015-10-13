package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;
import sb.jsonapi.JSSMAState;
import sb.jsonapi.MSJson;

public class FSMSMAStateGetter extends OneShotBehaviour {
	private JSSMAState _smaState;
	
	public FSMSMAStateGetter(int roomId) {
		_smaState = MSJson.getSMAStateByRoom(roomId);
	}
	
	@Override
	public void action() {
		_smaState = MSJson.getSMAStateByRoom(_smaState.Id); //Update on call
	}
	
	@Override
	public int onEnd() {
		if(_smaState.Manual)
			return 0;
		else
			return 1;
	}

}
