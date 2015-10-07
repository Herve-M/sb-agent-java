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
 * This behavior update the Agent 
 * @author Hervé
 *
 */
public class AgentDiscoveryBehaviour extends TickerBehaviour {
	
	private String			_roomNumber;
	private DefaultAgent	_agent;

	public AgentDiscoveryBehaviour(Agent a, String roomNumber) {
		super(a, 5000);
		_roomNumber = roomNumber;
		_agent = (DefaultAgent)a;
	}

	@Override
	protected void onTick() {
		DFAgentDescription researchTemplate = new DFAgentDescription();
		// Room Description
		ServiceDescription roomDescription = new ServiceDescription();
		roomDescription.setType(_roomNumber);
		
		researchTemplate.addServices(roomDescription);
		try {
			DFAgentDescription[] result = DFService.search(myAgent, researchTemplate); 
			if(result != null){
				for (DFAgentDescription dfAgentDescription : result) {
					if(!_agent.receivers.contains(dfAgentDescription) && dfAgentDescription.getName() != myAgent.getAID())
						_agent.receivers.add(dfAgentDescription.getName());
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
