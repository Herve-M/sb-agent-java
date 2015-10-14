/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.setup;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import sb.helpers.ClassificationHelper;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;

/**
 * The Class Room001.
 */
public class Room001 implements ISetup {
	
	/** The _args. */
	private Map<String, String> _args = new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#setArgs(java.lang.String[])
	 */
	@Override
	public void setArgs(String[] args) {

	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#setup()
	 */
	@Override
	public boolean setup() {
		try {
			Runtime runtime = Runtime.instance();
			//Container
			ProfileImpl impl = new ProfileImpl(false);
			impl.setParameter(Profile.MAIN_HOST, "localhost");
			impl.setParameter(Profile.CONTAINER_NAME, "CTN-101"); // Represents the living room and the kitchen.
			AgentContainer agentContainer = runtime.createAgentContainer(impl);
			
			List<AgentController> agents = new ArrayList<AgentController>();
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 1),
					"sb.agents.HLTAgent", new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 1),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 1),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 1),
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 2),
					"sb.agents.HLTAgent", new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 2),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 2),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 2),
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HLT, 3),
					"sb.agents.HLTAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.LUMINOSITY, 3),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.TEMPERATURE, 3),
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.HUMIDITY, 3),
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1),
					"sb.agents.PresenceAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 2),
					"sb.agents.PresenceAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 2)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1),
					"sb.agents.IOAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2),
					"sb.agents.IOAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 3),
					"sb.agents.IOAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 3)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 4),
					"sb.agents.IOAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 4)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 5),
					"sb.agents.IOAgent",
					new Object[]{
							"1",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 5)
						})
					);
			
			//Master
			agents.add(agentContainer.createNewAgent(
						ClassificationHelper.getCategoryCode(ECategoryHelper.AGENT, ETypeHelper.NONE),
						"sb.agents.MasterAgent",
						new Object[]{"1"}
					)
				);
			
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

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getDescription()
	 */
	@Override
	public String getDescription() {
		return new String("Deploy Room 001");
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().toString();
	}

}
