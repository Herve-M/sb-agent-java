package sb.interactioners;

import sb.actioners.IActioner;

public class ShutterInterActioner implements IInterActioner, IActioner {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean setValue(int value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTypeCode() {
		return "V";
	}

}
