package sb.agents;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sb.actioners.HumidityActioner;
import sb.actioners.LuminosityActioner;
import sb.actioners.TemperatureActioner;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.sensors.HumiditySensors;
import sb.sensors.LuminositySensors;
import sb.sensors.TemperatureSensors;

public class HLTAgent extends DefaultAgent {

	private String _strAgrs[] = new String[20];

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

	private void registerBehaviours() {
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration Behaviours");

	    addBehaviour(new LuminositySensors(this, new LuminosityActioner(_strAgrs[1])));
	    addBehaviour(new TemperatureSensors(this, new TemperatureActioner(_strAgrs[2])));
	    addBehaviour(new HumiditySensors(this, new HumidityActioner(_strAgrs[3])));
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
		sd1.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY));
		
		ad.addServices(sd1);
		
		ServiceDescription sd2 = new ServiceDescription();
		sd2.setName("CLASS");
		sd2.setOwnership(ad.getName().getName());
		sd2.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE));
		
		ad.addServices(sd2);
		
		ServiceDescription sd3 = new ServiceDescription();
		sd3.setName("CLASS");
		sd3.setOwnership(ad.getName().getName());
		sd3.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY));
		
		ad.addServices(sd3);
		
		ServiceDescription sd4 = new ServiceDescription();
		sd4.setName("ROOMID");
		sd4.setOwnership(ad.getName().getName());
		sd4.setType(_strAgrs[0]);
		
		ad.addServices(sd4);
		
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
