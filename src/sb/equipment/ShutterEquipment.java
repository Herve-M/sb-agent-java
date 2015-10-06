package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.LightInterActioner;
import sb.interactioners.ShutterInterActioner;

public class ShutterEquipment extends jade.core.behaviours.OneShotBehaviour {

	private boolean value;
	
	public ShutterEquipment(Agent a, boolean value) {
		super(a);
		this.value = value;
	}
	
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		ShutterInterActioner sia = new ShutterInterActioner(agent.targetedObject);
		sia.setValue(this.value ? 1 : 0);
	}

}
