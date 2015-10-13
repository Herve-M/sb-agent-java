/**
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update: 2015/10/09
 */
package sb.equipment;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import sb.agents.DefaultAgent;
import sb.interactioners.AirConditionerInterActioner;

/**
 * 
 *
 */
public class AirConditionerEquipment extends OneShotBehaviour{

	/** The value. */
	private int value;
	
	/**
	 * 
	 */
	public AirConditionerEquipment(Agent a, int value) {
		super(a);
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		DefaultAgent agent = (DefaultAgent) myAgent;
		AirConditionerInterActioner hia = new AirConditionerInterActioner(agent.targetedObject);
		hia.setValue(this.value);
//		System.out.println("[JSON] Update AC to :"+this.value);
	}

}
