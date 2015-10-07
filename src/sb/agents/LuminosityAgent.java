package sb.agents;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;

public class LuminosityAgent extends DefaultAgent {

/*	private String strAgrs[] = new String[20];

	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				strAgrs [i] = (String) args[i];
			}
		}
		else {
			doDelete();
		}
		// TODO Auto-generated method stub
		super.setup();
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
	}*/

}
