package sb.actioners;

import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class TemperatureActioner implements IActioner {
	
	private String 	_name;
	private boolean _state;
	private int 	_value;
	
	public TemperatureActioner(String Name) {
		_name = Name;	
		updateData();
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipement(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Integer.parseInt(equipement.value);
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getTypeCode() {
		return "T";
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
}
