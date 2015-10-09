/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.jsonapi;


/**
 * The Enum ENetAssembly.
 */
public enum ENetAssembly {
	
	/** The Bool. */
	Bool("System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089"),
	
	/** The Int32. */
	Int32("System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089");
	
	/**
	 * Instantiates a new e net assembly.
	 *
	 * @param code the code
	 */
	ENetAssembly(String code){
		this._code = code;
	}
	
	/** The _code. */
	private final String _code;
}
