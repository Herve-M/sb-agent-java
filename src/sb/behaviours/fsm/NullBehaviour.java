package sb.behaviours.fsm;

import jade.core.behaviours.OneShotBehaviour;

/**
 * Null Behaviour, used into FSM for non-conditional state.
 * @author Hervé
 *
 */
public class NullBehaviour extends OneShotBehaviour {
	@Override
	public void action() {
		//Do nothing
	}
}
