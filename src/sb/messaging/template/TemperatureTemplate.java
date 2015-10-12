/**
 * 
 */
package sb.messaging.template;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * @author Psyko
 *
 */
public class TemperatureTemplate implements MessageTemplate.MatchExpression {

	/* (non-Javadoc)
	 * @see jade.lang.acl.MessageTemplate.MatchExpression#match(jade.lang.acl.ACLMessage)
	 */
	@Override
	public boolean match(ACLMessage msg) {
    	String ontology = msg.getOntology();
    	return (ontology != null && ontology.contains("temperature"));
	}

}
