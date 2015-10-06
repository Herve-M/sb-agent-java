package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.LightInterActioner;

public class LightEquipment extends jade.core.behaviours.OneShotBehaviour {

	private boolean value;
	
	public LightEquipment(Agent a, boolean value) {
		super(a);
		this.value = value;
	}
	
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		LightInterActioner lia = new LightInterActioner(agent.targetedObject);
		lia.setValue(this.value ? 1 : 0);
	} 

}
