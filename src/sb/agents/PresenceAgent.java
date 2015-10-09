/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.EnumSet;

import sb.behaviours.EBehaviour;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;


/**
 * The Class PresenceAgent.
 */
public class PresenceAgent extends DefaultAgent {
	
	/** The _str agrs. */
	private String _strAgrs[] = new String[20];

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				_strAgrs[i] = (String) args[i];
			}
			
			registerDescription(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE);
			
			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.PresenceSensors);			
			registerBehaviours(behaviours);
		}
		else {
			doDelete();
		}
	}
}
