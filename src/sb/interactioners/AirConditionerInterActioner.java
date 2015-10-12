/**
 * @author MATYSIAK Hervé
 */
package sb.interactioners;

import sb.actioners.IActioner;
import sb.jsonapi.JSEquipement;
import sb.jsonapi.MSJson;

/**
 * The Class AirConditionerInterActioner.
 */
public class AirConditionerInterActioner implements IActioner, IInterActioner {

	/** The _name. */
	private String 	_name;
	
	/** The _state. */
	private boolean _state;
	
	/** The _value. */
	private int 	_value;
	
	/**
	 * 
	 */
	public AirConditionerInterActioner(String airConditionerName) {
		_name = airConditionerName;
		updateData();
	}
	
	/* (non-Javadoc)
	 * @see sb.interactioners.IInterActioner#setValue(int)
	 */
	@Override
	public boolean setValue(int value) {
		return MSJson.updateEquipment(_name, String.valueOf(value));
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
		return "A";
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getState()
	 */
	@Override
	public boolean getState() {
		updateData();
		return _state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(boolean state) {
		this._state = state;
	}

	/* (non-Javadoc)
	 * @see sb.actioners.IActioner#getValue()
	 */
	@Override
	public int getValue() {
		updateData();
		return _value;
	}
	
	/**
	 * Update data.
	 */
	private void updateData() {
		JSEquipement equipement = MSJson.getEquipment(_name);
		//TODO see Type ?
		this._value = Integer.parseInt(equipement.value);
		this._state = equipement != null && _value != 0 ? true : false;
	}

}
