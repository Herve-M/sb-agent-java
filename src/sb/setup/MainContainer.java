/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.setup;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;

/**
 * The Class MainContainer.
 */
public class MainContainer implements ISetup {

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
			
			//Main
			Properties p = new ExtendedProperties();
			p.setProperty("gui", "true");
			ProfileImpl impl2 = new ProfileImpl(p);
			AgentContainer mainContainer = runtime.createMainContainer(impl2);
			return true;
		} catch (Exception e) {
			System.err.println("Can't start main container : "+e.getMessage());
			System.err.println("Stacktrace : "+e.getStackTrace());
		}		
		return false;
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getDescription()
	 */
	@Override
	public String getDescription() {
		return new String("Deploy JADE Main Container");
	}

	/* (non-Javadoc)
	 * @see sb.setup.ISetup#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().toString();
	}

}
