/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.interactioners;

import sb.actioners.IActioner;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;


/**
 * The Class LightInterActioner.
 */
public class LightInterActioner implements IActioner, IInterActioner {
	
	/** The _name. */
	private String 	_name;
	
	/** The _state. */
	private boolean _state;
	
	/** The _value. */
	private boolean _value;
	
	/**
	 * Instantiates a new light inter actioner.
	 *
	 * @param lightName the light name
	 */
	public LightInterActioner(String lightName) {
		_name = lightName;	
		updateData();
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getName()
	 */
	@Override
	public String getName() {
		return _name;
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getState()
	 */
	@Override
	public boolean getState() {
		updateData();
		return _state;
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getValue()
	 */
	@Override
	public int getValue() {
		updateData();
		return _value ? 1 : 0;
	}

	/* (non-Javadoc)
	 * @see sb.interactioners.IInterActioner#setValue(int)
	 */
	@Override
	public boolean setValue(int value) {
		return MSJson.updateEquipment(_name, String.valueOf(value == 1 ? true : false));
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getTypeCode()
	 */
	@Override
	public String getTypeCode() {
		return "L";
	}
	
	/**
	 * Update data.
	 */
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Boolean.parseBoolean(equipement.value);
	}

}
