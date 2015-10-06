package sb.interactioners;

import sb.actioners.IActioner;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class LightInterActioner implements IActioner, IInterActioner {
	
	private String 	_name;
	private boolean _state;
	private boolean _value;
	
	public LightInterActioner(String lightName) {
		_name = lightName;	
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
		return _value ? 1 : 0;
	}

	@Override
	public boolean setValue(int value) {
		return MSJson.updateEquipement(_name, false, String.valueOf(value == 1 ? true : false));
	}

	@Override
	public String getTypeCode() {
		return "L";
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipement(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Boolean.parseBoolean(equipement.value);
	}

}
