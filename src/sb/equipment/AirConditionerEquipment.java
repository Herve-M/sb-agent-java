/**
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update: 2015/10/09
 */
package sb.equipment;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import sb.agents.DefaultAgent;
import sb.interactioners.AirConditionerInterActioner;

/**
 * The Class AirConditionerEquipment.
 */
public class AirConditionerEquipment extends OneShotBehaviour{

	/** The value. */
	private int _value;
	
	/**
	 * Instantiates a new air conditioner equipment.
	 *
	 * @param a the selected agent
	 * @param value the int value
	 */
	public AirConditionerEquipment(Agent a, int value) {
		super(a);
		this._value = value;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		AirConditionerInterActioner hia = new AirConditionerInterActioner(agent.targetedObject);
		hia.setValue(this._value);
	}

}
