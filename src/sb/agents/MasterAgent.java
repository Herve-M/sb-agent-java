/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.EnumSet;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sb.behaviours.AgentDiscoveryBehaviour;
import sb.behaviours.EBehaviour;
import sb.helpers.ClassificationHelper;
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
			
			EnumSet<EBehaviour> behaviours = EnumSet.of(EBehaviour.AgentDiscovery);			
			registerBehaviours(behaviours);
		}
		else {
			doDelete();
		}
	}
}
