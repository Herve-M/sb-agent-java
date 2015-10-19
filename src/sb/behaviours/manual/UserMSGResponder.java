package sb.behaviours.manual;

import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import sb.agents.DefaultAgent;
import sb.behaviours.TemperatureBehaviour;
import sb.helpers.EUserAction;

public class UserMSGResponder extends CyclicBehaviour {
	private EUserAction		_action;
	private EUserAction		_lastAction;
//	private Date			_lastDate;
	
	public UserMSGResponder(Agent a) {
		super(a);
	}
	
	public void action() {
		MessageTemplate mt = MessageTemplate.and(
		  		MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST),
		  		MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			ACLMessage reply = msg.createReply();
			String msgContent = msg.getContent();
			_action = EUserAction.valueOf(msgContent);
			
//			Date dt = msg.getReplyByDate();
//			int secondsBetween = (int) ((.getTime() - dt.getTime()) / 1000);
			
			if(_action == _lastAction)
				return;
			else {
				_lastAction = _action;
//				_lastDate = dt;
			}
				
			
			JOptionPane jOptionPane = null; 
			switch (_action) {
			case HTON:
				jOptionPane = new JOptionPane("Allumer le chauffage ?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				break;
			case HTOFF:
				jOptionPane = new JOptionPane("Etteindre le chauffage ?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				break;
			case ACON:
				jOptionPane = new JOptionPane("Allumer la climatisation ?",JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				break;
			default:
				break;
			}
			
			if(jOptionPane != null){
				JDialog dialog = jOptionPane.createDialog(null, "SMA-USER-DIALOG");
			    dialog.setVisible(true);
			    int selectedValue = (Integer)jOptionPane.getValue();
			    
			    switch (selectedValue) {
				case JOptionPane.YES_OPTION:
					reply.setPerformative(ACLMessage.AGREE);
					break;
				case JOptionPane.NO_OPTION:
					reply.setPerformative(ACLMessage.REFUSE);
					break;
				default:
					break;
				}
			}
			reply.setContent(_action.toString());
			myAgent.send(reply);
		}
		else {
			block();
		}
	}
}
