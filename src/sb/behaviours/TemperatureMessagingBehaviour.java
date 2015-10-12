/**
 * 
 */
package sb.behaviours;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import sb.actioners.TemperatureActioner;
import sb.jsonapi.ENetType;
import sb.messaging.template.TemperatureTemplate;

/**
 * @author Psyko
 *
 */
public class TemperatureMessagingBehaviour extends DefaultMessageReceiverBehaviour{

	private TemperatureActioner _temperature;
	private List<AID> _receivers = new ArrayList<>();
	
	/**
	 * @param a
	 */
	public TemperatureMessagingBehaviour(Agent a, TemperatureActioner toWatch) {
		super(a);
		this._temperature = toWatch;
		this._template = new MessageTemplate(new TemperatureTemplate());
	}

	/* (non-Javadoc)
	 * @see sb.behaviours.DefaultMessageReceiverBehaviour#action()
	 */
	@Override
	public void action() {
		
		//TODO: Get surrounding temperature
		//int result = _temperature.getAmbientTemperature();
		//System.out.println("Ambient Temperature : " + result);
		
		//TODO7: Get the user's preferences as min/max Temperature
		// int tempMax = _temperature.getMaxTemperature();
		// int tempMin = _temperature.getMinTemperature();
		
		//TODO: Assess the value : higher/lower/between the min and/or max desired values.
		
		//TODO : Target the right object between Heating or airconditioner
		//need to look at the automation diagram.
		
		//TODO : Construct ACLMessage
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setLanguage("English");
        msg.setOntology(ENetType.TEMPERATURE+"-REQUEST");
        //msg.setContent(_action.toString());
		
		//TODO : Find the right receivers
        for (AID agent : _receivers) {
                msg.addReceiver(agent);
        }
		
		//TODO : Send message
        myAgent.send(msg);
	}
}
