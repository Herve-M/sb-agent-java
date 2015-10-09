/*
 * @author MATYSIAK Hervé
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.actioners;


/**
 * The Interface IActioner.
 */
public interface IActioner {
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String 		getName();
	
	/**
	 * Gets the type code.
	 *
	 * @return the type code
	 */
	String 		getTypeCode();
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	boolean		getState();
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	int 		getValue();
}
