/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/13
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
import sb.interactioners.AirConditionerInterActioner;
import sb.interactioners.HeatingInterActioner;
import sb.interactioners.LightInterActioner;
import sb.interactioners.ShutterInterActioner;
import sb.jsonapi.ENetType;
import sb.sensors.AirConditionerSensors;
import sb.sensors.AirConditionnerMSGResponder;
import sb.sensors.HeatingMSGResponder;
import sb.sensors.HeatingSensors;
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
	public List<AID> 			receivers = new ArrayList<>();	
	
	/** Agent Service Description */
	public DFAgentDescription 	agentDescription = new DFAgentDescription();
	
	/** The targeted object. */
	public String 				targetedObject;
	
	/** The _str agrs. */
	public String 				strAgrs[] = new String[20];
	
	private int 				_serviceDescriptionCounter = 0;
	
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
					targetedObject = strAgrs[1];
					break;
				case PresenceSensors:
					addBehaviour(new PresenceSensors(this, new PresenceActioner(strAgrs[1])));
					break;
				case LightSensors:
					addBehaviour(new LightSensors(this, new LightInterActioner(strAgrs[1])));
					targetedObject = strAgrs[1];
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
				case HeatingSensors:
					addBehaviour(new HeatingSensors(this, new HeatingInterActioner(strAgrs[1])));
					targetedObject = strAgrs[1];
					break;
				case AirConditionerSensors:
					addBehaviour(new AirConditionerSensors(this, new AirConditionerInterActioner(strAgrs[1])));
					targetedObject = strAgrs[1];
					break;
				case HeatingMSGResponder:
					addBehaviour(new HeatingMSGResponder(this));
					break;
				case AirConditionnerMSGResponder:
					addBehaviour(new AirConditionnerMSGResponder(this));
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
	public void registerDescription(ECategoryHelper categoryHelper, ETypeHelper typeHelper){
		System.out.println("Agent : " 
				+ getAID().getName()
				+ "\n\t"
				+ "Registration");
		
		agentDescription.setName(getAID());
		
		ServiceDescription sd1 = new ServiceDescription();
		sd1.setName("CLASS");
		sd1.setOwnership(agentDescription.getName().getName());
		sd1.setType(ClassificationHelper.getCategoryCode(categoryHelper, typeHelper));
		
		agentDescription.addServices(sd1);
		
		if(_serviceDescriptionCounter < 1){
			ServiceDescription sd2 = new ServiceDescription();
			sd2.setName("ROOMID");
			sd2.setOwnership(agentDescription.getName().getName());
			sd2.setType(strAgrs[0]);
			agentDescription.addServices(sd2);
			_serviceDescriptionCounter += 1;
		}			
	}
	
	public boolean registerAgent() {
		try {
			DFService.register(this, agentDescription);
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
