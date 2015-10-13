/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.helpers;


/**
 * The Enum ECategoryHelper.
 */
public enum ECategoryHelper {
	
	/** The sensor. */
	SENSOR("CAP"),
	
	/** The actioner. */
	ACTIONER("ACT"),
	
	/** The agent. */
	AGENT("AGT"),
	
	/** The container. */
	CONTAINER("CTN");
	
	/**
	 * Instantiates a new e category helper.
	 *
	 * @param code the code
	 */
	ECategoryHelper(String code){
		this._code = code;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return  _code;
	}
	
	/** The _code. */
	private final String _code;				
}
