/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import sb.agents.DefaultAgent;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;


/**
 * This behavior update the Agent.
 *
 * @author Hervé
 */
public class AgentDiscoveryBehaviour extends TickerBehaviour {
	
	/** The _room number. */
	private String			_roomNumber;
	
	/** The _agent. */
	private DefaultAgent	_agent;

	/**
	 * Instantiates a new agent discovery behaviour.
	 *
	 * @param a the a
	 * @param roomNumber the room number
	 */
	public AgentDiscoveryBehaviour(Agent a, String roomNumber) {
		super(a, 5000);
		_roomNumber = roomNumber;
		_agent = (DefaultAgent)a;
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	protected void onTick() {
		DFAgentDescription researchTemplate = new DFAgentDescription();
		// Agent Description
		ServiceDescription agentDescription = new ServiceDescription();
		agentDescription.setName("CLASS");
		agentDescription.setType(ClassificationHelper.getCategoryCode(ECategoryHelper.AGENT, ETypeHelper.NONE));
		// Room Description
		ServiceDescription roomDescription  = new ServiceDescription();
		roomDescription.setName("ROOMID");
		roomDescription.setType(_roomNumber);
		
		researchTemplate.addServices(agentDescription);
		researchTemplate.addServices(roomDescription);
		try {
			DFAgentDescription[] result = DFService.search(myAgent, researchTemplate); 
			if(result != null){
				for (DFAgentDescription dfAgentDescription : result) {
					if(!_agent.receivers.contains(dfAgentDescription.getName()) && dfAgentDescription.getName() != myAgent.getAID()){
						_agent.receivers.add(dfAgentDescription.getName());
						System.out.println(_agent.receivers.toString());
					}
				}
			} else {
				_agent.receivers.clear();
			}			
		}
		catch (FIPAException fe) {
			System.err.println("AGT-DISC-BHR Err : "+fe.getMessage());
		}
	}

}
