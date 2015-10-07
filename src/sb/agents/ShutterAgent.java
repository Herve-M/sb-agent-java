package sb.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;

public class ShutterAgent extends DefaultAgent {

	private String _strAgrs[] = new String[20];

	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				_strAgrs [i] = (String) args[i];
			}
		}
		else {
			doDelete();
		}
		
		registerDescription();
	}

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
		sd1.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.ACTIONER, ETypeHelper.SHUTTER));
		
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
	
	@Override
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
		} catch (FIPAException  e) {
			e.printStackTrace();
		}
		
		System.out.println("Agent SHUTDOWN : " + getAID().getName());
		
		super.takeDown();
	}

}
