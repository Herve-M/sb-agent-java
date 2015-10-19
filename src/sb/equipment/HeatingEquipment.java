/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.HeatingInterActioner;


/**
 * The Class HeatingEquipment.
 */
public class HeatingEquipment extends jade.core.behaviours.OneShotBehaviour {

	/** The value. */
	private int value;
	
	/**
	 * Instantiates a new heating equipment.
	 *
	 * @param a the a
	 * @param value the value
	 */
	public HeatingEquipment(Agent a, int value) {
		super(a);
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		HeatingInterActioner hia = new HeatingInterActioner(agent.targetedObject);
		hia.setValue(this.value);
//		System.out.println("[JSON] Update H to :"+this.value);
	}
}
