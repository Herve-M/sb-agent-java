package sb.interactioners;

import sb.actioners.IActioner;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class ShutterInterActioner implements IInterActioner, IActioner {
	
	private String 	_name;
	private boolean _state;
	private int 	_value;

	public ShutterInterActioner(String shutterName) {
		_name = shutterName;	
		updateData();
	}
	
	@Override
	public String getName() {
		return _name;
	}

	@Override
	public boolean getState() {
		updateData();
		return _state;
	}

	@Override
	public int getValue() {
		updateData();
		return _value;
	}

	@Override
	public boolean setValue(int value) {
		return MSJson.updateEquipement(_name, false, String.valueOf(_value));
	}

	@Override
	public String getTypeCode() {
		return "V";
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipement(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Integer.parseInt(equipement.value);
	}

}
