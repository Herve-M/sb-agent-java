/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.jsonapi;


/**
 * ID of Type used in WebService
 */
///Get_AllTypes
public enum ENetType {
	
	/** The presence. */
	PRESENCE("5"),
	
	/** The temperature. */
	TEMPERATURE("2"),
	
	/** The luminosity. */
	LUMINOSITY("4"),
	
	/** The humidity. */
	HUMIDITY("3"),
	
	/** The io. */
	IO("1"),
	
	/** The shutter. */
	SHUTTER("7"),
	
	/** The light. */
	LIGHT("6"),
	
	/** The heating. */
	HEATING("8"), 
	
	/** The air conditioner. */
	AIRCONDITIONER("9");
	
	/**
	 * Instantiates a new e net type.
	 *
	 * @param code the code
	 */
	ENetType(String code){
		this._code = code;
	}
	
	/**
	 * To code.
	 *
	 * @return the string
	 */
	public String toCode() {
		return _code;
	}
	
	/** The _code. */
	private final String _code;
}
