/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.EnumSet;

import sb.behaviours.EBehaviour;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;


/**
 * The Class ShutterAgent.
 */
public class ShutterAgent extends DefaultAgent {

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
			
			registerDescription(ECategoryHelper.SENSOR, ETypeHelper.SHUTTER);
			registerAgent();
			
			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.ShutterSensors, EBehaviour.AgentDiscovery);			
			registerBehaviours(behaviours);
		}
		else {
			doDelete();
		}
	}

}
