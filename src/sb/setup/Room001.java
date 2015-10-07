package sb.setup;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;

public class Room001 implements ISetup {
	
	private Map<String, String> _args = new HashMap<String, String>();

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean setup() {
		try {
			Runtime runtime = Runtime.instance();
			//Container
			ProfileImpl impl = new ProfileImpl(false);
			impl.setParameter(ProfileImpl.MAIN_HOST, "localhost");
			impl.setParameter(ProfileImpl.CONTAINER_NAME, "CTN-101");
			AgentContainer agentContainer = runtime.createAgentContainer(impl);
			
			List<AgentController> agents = new ArrayList<AgentController>();
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 1),
					"sb.agents.HLTAgent", new Object[]{
							"1",
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 1),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 1),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 1),
							}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 2),
					"sb.agents.HLTAgent", new Object[]{
							"1",
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 2),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 2),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 2),
							}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 3),
					"sb.agents.HLTAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 3),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 3),
							ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 3),
							}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1),
					"sb.agents.PresenceAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 2),
					"sb.agents.PresenceAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 2)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1),
					"sb.agents.IOAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2),
					"sb.agents.IOAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 3),
					"sb.agents.IOAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 3)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 4),
					"sb.agents.IOAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 4)}));
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 5),
					"sb.agents.IOAgent", new Object[]{"1", ClassificationHelper.getClassifcationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 5)}));
			
			//Master
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getCategoryCode(ECategoryHelper.AGENT, ETypeHelper.NONE),
					"sb.agents.MasterAgent", new Object[]{"1"}));
			
			agentContainer.start();
			
			for (AgentController agentController : agents) {
				agentController.start();
			}
			
			return true;
		} catch (Exception e) {
			System.err.println("Can't start : "+e.getMessage());
		}
		return false;
	}

	@Override
	public String getDescription() {
		return new String("Room 001 Setup");
	}

	@Override
	public String getName() {
		return this.getClass().toString();
	}

}
