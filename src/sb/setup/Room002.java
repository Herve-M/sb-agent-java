/**
 * 
 */
package sb.setup;

import java.util.ArrayList;
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
 * @author Psyko
 *
 */
public class Room002 implements ISetup {

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#setArgs(java.lang.String[])
	 */
	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub

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
			impl.setParameter(Profile.CONTAINER_NAME, "CTN-102"); // Represents the bedroom
			AgentContainer agentContainer = runtime.createAgentContainer(impl);
			
			List<AgentController> agents = new ArrayList<AgentController>();

			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1),
					"sb.agents.PresenceAgent",
					new Object[]{
							"2",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.PRESENCE, 1)
						})
					);
			
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1),
					"sb.agents.IOAgent",
					new Object[]{
							"2",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 1)
						})
					);
			agents.add(agentContainer.createNewAgent(ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2),
					"sb.agents.IOAgent",
					new Object[]{
							"2",
							ClassificationHelper.getClassificationCode(ECategoryHelper.SENSOR, ETypeHelper.IO, 2)
						})
					);
			
			//Master
			agents.add(agentContainer.createNewAgent(
						ClassificationHelper.getCategoryCode(ECategoryHelper.AGENT, ETypeHelper.NONE),
						"sb.agents.MasterAgent",
						new Object[]{"2"}
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
		return new String("Deploy Room 002");
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().getName();
	}

}
