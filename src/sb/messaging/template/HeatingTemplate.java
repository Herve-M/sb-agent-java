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
public class HeatingTemplate implements MessageTemplate.MatchExpression{

	/* (non-Javadoc)
	 * @see jade.lang.acl.MessageTemplate.MatchExpression#match(jade.lang.acl.ACLMessage)
	 */
	@Override
	public boolean match(ACLMessage msg) {
    	String ontology = msg.getOntology();
    	return (ontology != null && ontology.contains("heating"));
	}

}
