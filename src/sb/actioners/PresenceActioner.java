package sb.actioners;

import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class PresenceActioner implements IActioner {
	
	private String 	_name;
	private boolean _state;
	private boolean _value;
	
	public PresenceActioner(String Name) {
		_name = Name;	
		updateData();
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Boolean.parseBoolean(equipement.value);
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getTypeCode() {
		return "P";
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
}
