package sb.agents;

import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import sb.behaviours.AgentDiscoveryBehaviour;
import sb.helpers.ClassificationHelper;

public class DefaultAgent extends Agent {
	public List<AID> 	receivers;
	private String strAgrs[] = new String[20];
	public String targetedObject;
	
	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());
		
		Object args[] = getArguments();
		if(args != null && args.length > 0){
			for (int i = 0; i < args.length; i++) {
				strAgrs[i] = (String) args[i];
			}
		}
		else {
			doDelete();
		}
		
		//Directory
		DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(getAID());
		ServiceDescription serviceDescription = new ServiceDescription();
		//TODO
		//serviceDescription.setType(ClassificationHelper.getCategoryCode(category, type));
		//serviceDescription.setName(ClassificationHelper.getClassifcationCode(category, type, number));
		agentDescription.addServices(serviceDescription);
		
		try {
			DFService.register(this, agentDescription);
		} catch (FIPAException  e) {
			e.printStackTrace();
		}
	    
	    //Behavior
	    //addBehaviour(AgentDiscoveryBehaviour(this, ));
		//addBehaviour(LightEquipment(this, false));
		
		//doDelete();
	}	
	
	@Override
	protected void takeDown() {
		
		try {
			DFService.deregister(this);
		} catch (FIPAException  e) {
			e.printStackTrace();
		}
		
		System.out.println("Agent SHUTDOWN : "+getAID().getName());
	}
}
