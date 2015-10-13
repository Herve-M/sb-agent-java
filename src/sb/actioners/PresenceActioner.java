/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.actioners;

import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;


/**
 * The Class PresenceActioner.
 */
public class PresenceActioner implements IActioner {
	
	/** The _name. */
	private String 	_name;
	
	/** The _state. */
	private boolean _state;
	
	/** The _value. */
	private boolean _value;
	
	/**
	 * Instantiates a new presence actioner.
	 *
	 * @param Name the name
	 */
	public PresenceActioner(String Name) {
		_name = Name;
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

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getName()
	 */
	@Override
	public String getName() {
		return _name;
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getTypeCode()
	 */
	@Override
	public String getTypeCode() {
		return "P";
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
}
