/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/13
 */
package sb.agents;

import java.util.EnumSet;

import jade.core.behaviours.TickerBehaviour;
import sb.behaviours.EBehaviour;
import sb.behaviours.TemperatureBehaviour;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;


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
			
//			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.TemperatureBehaviour);			
//			registerBehaviours(behaviours);
			
			addBehaviour(new TickerBehaviour(this, 1300) {
				
				@Override
				protected void onTick() {
					myAgent.addBehaviour(new TemperatureBehaviour(myAgent, 1));				
				}
			});
			
		}
		else {
			doDelete();
		}
	}
}
