package sb.interactioners;

import sb.actioners.IActioner;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class HeatingInterActioner implements IActioner, IInterActioner {
	
	private String 	_name;
	private boolean _state;
	private int 	_value;

	public HeatingInterActioner(String heatingName) {
		_name = heatingName;	
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
		return MSJson.updateEquipment(_name, String.valueOf(value));
	}

	@Override
	public String getTypeCode() {
		return "C";
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Integer.parseInt(equipement.value);
	}

}
