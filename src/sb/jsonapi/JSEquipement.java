/*
 * @author MATYSIAK Herv�
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The Class JSEquipement.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class JSEquipement {
	
	/** The created. */
	@JsonProperty("Created")
	public String created;
	
	/** The id. */
	@JsonProperty("Id")
	public String id;
	
	/** The name. */
	@JsonProperty("Name")
	public String name;

/** The updated. */
//	private JSType type;
	@JsonProperty("Updated")
	public String updated;
	
	/** The value. */
	@JsonProperty("Value")
	public String value;
	
	/**
	 * Instantiates a new JS equipement.
	 */
	public JSEquipement() {
	}
}
