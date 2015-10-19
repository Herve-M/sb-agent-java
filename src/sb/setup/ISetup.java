/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.setup;


/**
 * The Interface ISetup.
 */
public interface ISetup {
	
	/**
	 * Sets the args.
	 *
	 * @param args the new args
	 */
	void 	setArgs(String[] args);
	
	/**
	 * Setup.
	 *
	 * @return true, if successful
	 */
	boolean setup();
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	String 	getDescription();
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String 	getName();
}
