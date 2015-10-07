package sb.actioners;

import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

public class HumidityActioner implements IActioner {
	
	private String 	_name;
	private boolean _state;
	private int 	_value;

	public HumidityActioner(String Name) {
		_name = Name;	
		updateData();
	}
	
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
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
		return "H";
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
