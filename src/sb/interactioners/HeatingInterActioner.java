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
 * The Class HeatingInterActioner.
 */
public class HeatingInterActioner implements IActioner, IInterActioner {
	
	/** The _name. */
	private String 	_name;
	
	/** The _state. */
	private boolean _state;
	
	/** The _value. */
	private int 	_value;

	/**
	 * Instantiates a new heating inter actioner.
	 *
	 * @param heatingName the heating name
	 */
	public HeatingInterActioner(String heatingName) {
		_name = heatingName;	
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
		return _value;
	}

	/* (non-Javadoc)
	 * @see sb.interactioners.IInterActioner#setValue(int)
	 */
	@Override
	public boolean setValue(int value) {
		return MSJson.updateEquipment(_name, String.valueOf(value));
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getTypeCode()
	 */
	@Override
	public String getTypeCode() {
		return "C";
	}
	
	/**
	 * Update data.
	 */
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
		//TODO see Type ?
		this._state = equipement != null ? true : false;
		this._value = Integer.parseInt(equipement.value);
	}

}
