package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;
import sb.jsonapi.JSSMAState;
import sb.jsonapi.MSJson;

/**
 * FSM State Behavior used to get the SMA state
 * Manual or Auto
 * @author Hervé
 *
 */
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
		boolean state = _smaState.Manual;
		System.out.println("FSM : SMA State is "+ (state ? "Manual" : "Auto"));
		if(state)
			return 0;
		else
			return 1;
	}

}
