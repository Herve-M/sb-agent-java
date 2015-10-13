/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.equipment;

import jade.core.Agent;
import sb.agents.DefaultAgent;
import sb.interactioners.ShutterInterActioner;


/**
 * The Class ShutterEquipment.
 */
public class ShutterEquipment extends jade.core.behaviours.OneShotBehaviour {

	/** The value. */
	private boolean value;
	
	/**
	 * Instantiates a new shutter equipment.
	 *
	 * @param a the a
	 * @param value the value
	 */
	public ShutterEquipment(Agent a, boolean value) {
		super(a);
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		ShutterInterActioner sia = new ShutterInterActioner(agent.targetedObject);
		sia.setValue(this.value ? 1 : 0);
	}

}
