/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import sb.jsonapi.ENetType;


/**
 * The Class DefaultAgent.
 */
public class DefaultAgent extends Agent {
	
	/** The receivers. */
	public List<AID> 	receivers = new ArrayList<>();	
	
	/** The targeted object. */
	public String 		targetedObject;
	
	
	/**
	 * Send inform.
	 *
	 * @param type the type
	 * @param value the value
	 */
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
	
	/**
	 * Send failure.
	 *
	 * @param type the type
	 */
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
