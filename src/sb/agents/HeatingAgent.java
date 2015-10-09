/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sb.behaviours.AgentDiscoveryBehaviour;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.interactioners.HeatingInterActioner;
import sb.sensors.HeatingSensors;


/**
 * The Class HeatingAgent.
 */
public class HeatingAgent extends DefaultAgent {

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
				_strAgrs [i] = (String) args[i];
			}
			
			registerDescription();
			
			registerBehaviours();
		}
		else {
			doDelete();
		}
	}

	/**
	 * Register behaviours.
	 */
	private void registerBehaviours() {
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration Behaviours");

		addBehaviour(new AgentDiscoveryBehaviour(this, _strAgrs[0]));
	    addBehaviour(new HeatingSensors(this, new HeatingInterActioner(_strAgrs[1])));
	}

	/**
	 * Register description.
	 */
	private void registerDescription() {
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration");
		
		DFAgentDescription ad = new DFAgentDescription();
		ad.setName(getAID());
		
		ServiceDescription sd1 = new ServiceDescription();
		sd1.setName("CLASS");
		sd1.setOwnership(ad.getName().getName());
		sd1.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.ACTIONER, ETypeHelper.HEATING));
		
		ad.addServices(sd1);
		
		ServiceDescription sd2 = new ServiceDescription();
		sd2.setName("ROOMID");
		sd2.setOwnership(ad.getName().getName());
		sd2.setType(_strAgrs[0]);
		
		ad.addServices(sd2);
		
		try {
			DFService.register(this, ad);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}
}
