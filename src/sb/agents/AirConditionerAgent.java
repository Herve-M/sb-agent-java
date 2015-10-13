/**
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Apdate : 2015/10/09
 */
package sb.agents;

import java.util.EnumSet;

import sb.behaviours.EBehaviour;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;

/**
 * 
 */
public class AirConditionerAgent extends DefaultAgent {
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				strAgrs [i] = (String) args[i];
			}
			
			registerDescription(ECategoryHelper.SENSOR, ETypeHelper.AIRCONDITIONER);
			registerAgent();
			
			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.AirConditionerSensors, EBehaviour.AgentDiscovery);			
			registerBehaviours(behaviours);
		}
		else {
			doDelete();
		}
	}
}
