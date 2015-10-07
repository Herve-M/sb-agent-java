package sb.agents;

import java.util.ArrayList;
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
import sb.jsonapi.ENetType;

public class DefaultAgent extends Agent {
	public List<AID> 	receivers = new ArrayList<>();	
	public String 		targetedObject;
	
	
	public void sendInform(ENetType type, String value){
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		for (AID agent : receivers) {
			msg.addReceiver(agent);
		}
		msg.setLanguage("English");
		msg.setOntology(type+"-EVENT");
		msg.setContent(value);
		this.send(msg);
		System.out.println(msg.toString());
	}
	
	public void sendFailure(ENetType type){
		ACLMessage msg = new ACLMessage(ACLMessage.FAILURE);
		for (AID agent : receivers) {
			msg.addReceiver(agent);
		}
		msg.setLanguage("English");
		msg.setOntology(type+"-EVENT");
		this.send(msg);
		System.out.println(msg.toString());
	}
}
