package sb.agents;

import jade.core.Agent;

public class TemperatureAgent extends Agent {

	private String strAgrs[] = new String[20];

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
		// TODO Auto-generated method stub
		super.takeDown();
	}

}
