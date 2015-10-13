/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.EnumSet;

import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import sb.behaviours.EBehaviour;
import sb.behaviours.TemperatureBehaviour;
import sb.behaviours.fsm.FSMTemperatureGetter;
import sb.helpers.EAction;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.sensors.HeatingMSGSender;


/**
 * The Class MasterAgent.
 */
public class MasterAgent extends DefaultAgent {

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
			
			registerDescription(ECategoryHelper.AGENT, ETypeHelper.NONE);
			registerAgent();
			
//			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.AgentDiscovery);			
//			registerBehaviours(behaviours);
			
			addBehaviour(new TemperatureBehaviour(this, 1));
		}
		else {
			doDelete();
		}
	}
}
