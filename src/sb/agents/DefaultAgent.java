/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.agents;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import sb.actioners.HumidityActioner;
import sb.actioners.IOActioner;
import sb.actioners.LuminosityActioner;
import sb.actioners.PresenceActioner;
import sb.actioners.TemperatureActioner;
import sb.behaviours.AgentDiscoveryBehaviour;
import sb.behaviours.EBehaviour;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;
import sb.interactioners.LightInterActioner;
import sb.interactioners.ShutterInterActioner;
import sb.jsonapi.ENetType;
import sb.sensors.HumiditySensors;
import sb.sensors.IOSensors;
import sb.sensors.LightSensors;
import sb.sensors.LuminositySensors;
import sb.sensors.PresenceSensors;
import sb.sensors.ShutterSensors;
import sb.sensors.TemperatureSensors;


/**
 * The Class DefaultAgent.
 */
public class DefaultAgent extends Agent {
	
	/** The receivers. */
	public List<AID> 	receivers = new ArrayList<>();	
	
	/** The targeted object. */
	public String 		targetedObject;
	
	/** The _str agrs. */
	public String 		strAgrs[] = new String[20];
	
	/**
	 * Add a behaviour
	 * Must be called in setup();
	 * @param behaviours
	 * @return
	 */
	public boolean registerBehaviours(EnumSet<EBehaviour> behaviours){
		
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration Behaviours");
		
		try {
			for (EBehaviour eBehavior : behaviours) {
				switch (eBehavior) {
				case AgentDiscovery:
					addBehaviour(new AgentDiscoveryBehaviour(this, strAgrs[0]));
					break;
				case ShutterSensors:
					addBehaviour(new ShutterSensors(this, new ShutterInterActioner(strAgrs[1])));
					break;
				case PresenceSensors:
					addBehaviour(new PresenceSensors(this, new PresenceActioner(strAgrs[1])));
					break;
				case LightSensors:
					addBehaviour(new LightSensors(this, new LightInterActioner(strAgrs[1])));
					break;
				case IOSensors:
					addBehaviour(new IOSensors(this, new IOActioner(strAgrs[1])));
					break;
				case LuminositySensors:
					addBehaviour(new LuminositySensors(this, new LuminosityActioner(strAgrs[1])));
					break;
				case TemperatureSensors:
					addBehaviour(new TemperatureSensors(this, new TemperatureActioner(strAgrs[2])));
					break;
				case HumiditySensors:
					addBehaviour(new HumiditySensors(this, new HumidityActioner(strAgrs[3])));
					break;
				default:
					System.err.println("Trying to register a unknow Behaviour");
					break;
				}
			}
		} catch(Exception ex){
			System.err.println("Errro at registerBehaviours "+ex.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Register to DF
	 * Must be called in setup.
	 * @param categoryHelper
	 * @param typeHelper
	 * @return
	 */
	public boolean registerDescription(ECategoryHelper categoryHelper, ETypeHelper typeHelper){
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration");
		
		DFAgentDescription ad = new DFAgentDescription();
		ad.setName(getAID());
		
		ServiceDescription sd1 = new ServiceDescription();
		sd1.setName("CLASS");
		sd1.setOwnership(ad.getName().getName());
		sd1.setType(ClassificationHelper.getCategoryCode(categoryHelper, typeHelper));
		
		ad.addServices(sd1);
		
		ServiceDescription sd2 = new ServiceDescription();
		sd2.setName("ROOMID");
		sd2.setOwnership(ad.getName().getName());
		sd2.setType(strAgrs[0]);
		
		ad.addServices(sd2);
		
		try {
			DFService.register(this, ad);
		} catch (FIPAException ex) {
			System.err.println("Errro at registerBehaviours "+ex.getMessage());
			return false;
		}
		return true;
	}
	
	
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
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException  e) {
			e.printStackTrace();
		}
		
		System.out.println("Agent SHUTDOWN : " + getAID().getName());
	}
}
