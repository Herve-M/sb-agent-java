/**
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Apdate : 2015/10/09
 */
package sb.agents;

/**
 * 
 */
public class AirConditionerAgent extends DefaultAgent {

	/** The _str agrs. */
	private String _strAgrs[] = new String[20];
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				_strAgrs [i] = (String) args[i];
			}
			
			registerDescription();
			
			registerBehaviours();
		}
		else {
			doDelete();
		}
	}

	/**
	 * 
	 */
	private void registerBehaviours() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void registerDescription() {
		// TODO Auto-generated method stub
		
	}
}
