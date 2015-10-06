package sb.actioners;

import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class IOActioner implements IActioner {
	
	private String 	_name;
	private boolean _state;
	private boolean _value;
	
	public IOActioner(String Name) {
		_name = Name;	
		updateData();
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipement(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Boolean.parseBoolean(equipement.value);
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
	public String getTypeCode() {
		return "F";
	}

}
