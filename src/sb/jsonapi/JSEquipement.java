/*
 * @author MATYSIAK Hervé
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
	
//	public String getCreated() {
//		return created;
//	}
//	public void setCreated(String created) {
//		created = created;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		name = name;
//	}
////	public JSType getType() {
////		return type;
////	}
////	public void setType(JSType type) {
////		type = type;
////	}
//	public String getUpdated() {
//		return updated;
//	}
//	public void setUpdated(String updated) {
//		updated = updated;
//	}
//	public String getValue() {
//		return value;
//	}
//	public void setValue(String value) {
//		value = value;
//	}
}
