package sb.setup;

import java.util.Map;
import java.util.HashMap;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

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
			//TODO Add Agent
			AgentController masterController = agentContainer.createNewAgent("AGT-001", "sb.agents.DoorMasterAgent", new Object[]{});
			//ENDTODO
			return true;
		} catch (Exception e) {
			System.err.println("Can't start Main Container : "+e.getMessage());
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
