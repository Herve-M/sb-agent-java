package sb.setup;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;

public class MainContainer implements ISetup {

	@Override
	public void setArgs(String[] args) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public String getDescription() {
		return new String("Deploy JADE Main Container");
	}

	@Override
	public String getName() {
		return this.getClass().toString();
	}

}
