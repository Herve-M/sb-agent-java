/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.LightInterActioner;


/**
 * The Class LightEquipment.
 */
public class LightEquipment extends jade.core.behaviours.OneShotBehaviour {

	/** The value. */
	private boolean value;
	
	/**
	 * Instantiates a new light equipment.
	 *
	 * @param a the a
	 * @param value the value
	 */
	public LightEquipment(Agent a, boolean value) {
		super(a);
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		LightInterActioner lia = new LightInterActioner(agent.targetedObject);
		lia.setValue(this.value ? 1 : 0);
	} 

}
