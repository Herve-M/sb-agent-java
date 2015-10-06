package sb.sensors;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import sb.actioners.PresenceActioner;
import sb.agents.DefaultAgent;

import jade.core.behaviours.TickerBehaviour;

@SuppressWarnings("serial")
public class PresenceSensors extends TickerBehaviour {
	private PresenceActioner	_presence;
	private DefaultAgent 		_defaultAgent;
	private int 				_presenceState;

	public PresenceSensors(Agent a, PresenceActioner presenceToWatch) {
		super(a, 5000);
		_defaultAgent = (DefaultAgent) a;
		_presence = presenceToWatch;
		_presenceState = _presence.getValue();
	}

	@Override
	protected void onTick() {
		if(_presence.getState()){
			if(_presence.getValue() != _presenceState){
				//TODO sendMSG
			}
		}
	}

}
