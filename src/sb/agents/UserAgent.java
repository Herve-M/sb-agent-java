package sb.agents;

import java.util.EnumSet;

import sb.behaviours.EBehaviour;
import sb.behaviours.manual.UserMSGResponder;
import sb.behaviours.manual.UserMSGResponder;
import sb.helpers.ECategoryHelper;
import sb.helpers.ETypeHelper;

/**
 * A user agent. Used only when SMA run on MANUAL mod
 * on client side !
 * @author Hervé
 *
 */
public class UserAgent extends DefaultAgent {

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		System.out.println("Agent INIT : " + getAID().getName());

		Object args[] = getArguments();
		if(args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				strAgrs [i] = (String) args[i];
			}
			
			registerDescription(ECategoryHelper.USER, ETypeHelper.NONE);
			registerAgent();
			
			addBehaviour(new UserMSGResponder(this));
		}
		else {
			doDelete();
		}
	}

}
