/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.helpers;


/**
 * The Enum ETypeHelper.
 */
public enum ETypeHelper {
	
	/** The presence. */
	PRESENCE("P"),
	
	/** The temperature. */
	TEMPERATURE("T"),
	
	/** The luminosity. */
	LUMINOSITY("L"),
	
	/** The humidity. */
	HUMIDITY("H"),
	
	/** The io. */
	IO("F"),
	
	/** The shutter. */
	SHUTTER("V"),
	
	/** The light. */
	LIGHT("L"),
	
	/** The heating. */
	HEATING("C"),
	
	/** The hlt. */
	HLT("HLT"),
	
	/** The Air Conditioner. */
	AIRCONDITIONER("A"),
	
	/** The none. */
	NONE("N");
	
	/**
	 * Instantiates a new e type helper.
	 *
	 * @param code the code
	 */
	ETypeHelper(String code){
		this._code = code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return _code;
	}
	
	/** The _code. */
	private final String _code;
}
