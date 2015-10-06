package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.HeatingInterActioner;
import sb.interactioners.LightInterActioner;

public class HeatingEquipment extends jade.core.behaviours.OneShotBehaviour {

	private int value;
	
	public HeatingEquipment(Agent a, int value) {
		super(a);
		this.value = value;
	}
	
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		HeatingInterActioner hia = new HeatingInterActioner(agent.targetedObject);
		hia.setValue(this.value);
		
	}

}
