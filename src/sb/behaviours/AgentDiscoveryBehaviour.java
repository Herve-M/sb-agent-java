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

public class AgentDiscoveryBehaviour extends TickerBehaviour {
	
	private ECategoryHelper _category;
	private ETypeHelper 	_type;
	private int				_roomNumber;
	private DefaultAgent	_agent;
	private Agent 			_jadeAgent;

	public AgentDiscoveryBehaviour(Agent a, ECategoryHelper category, ETypeHelper type, int roomNumber) {
		super(a, 5000);
		_category = category;
		_type = type;
		_roomNumber = roomNumber;
		_agent = (DefaultAgent)a;
	}

	@Override
	protected void onTick() {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType(ClassificationHelper.getCategoryCode(_category, _type));
		template.addServices(sd);
		try {
			DFAgentDescription[] result = DFService.search(_jadeAgent, template); 
			for (DFAgentDescription dfAgentDescription : result) {
				_agent.receivers.add(dfAgentDescription.getName());
			}			
		}
		catch (FIPAException fe) {
			System.err.println("AGT-DISC-BHR Err : "+fe.getMessage());
		}
	}

}
