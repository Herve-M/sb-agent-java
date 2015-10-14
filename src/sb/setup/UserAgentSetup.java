/**
 * 
 */
package sb.setup;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;

/**
 * @author Psyko
 *
 */
public class UserAgentSetup implements ISetup {

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
			
			ProfileImpl p = new ProfileImpl(false);
			p.setParameter(Profile.CONTAINER_NAME, "CTN-USERAGENT");
			
			AgentContainer container = runtime.createAgentContainer(p);
			
			AgentController agent = container.createNewAgent("USERAGENT", "sb.agents.UserAgent", new Object[]{"1"});
			
			container.start();
			
			agent.start();
			
			return true;
		} catch (Exception e) {
			System.err.println("Can't start container : " + e.getMessage());
			System.err.println("Stacktrace : " + e.getStackTrace());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
