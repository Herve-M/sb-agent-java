package sb.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jdk.nashorn.internal.runtime.JSType;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JSEquipement {
	@JsonProperty("Created")
	public String created;
	@JsonProperty("Id")
	public String id;
	@JsonProperty("Name")
	public String name;
//	private JSType type;
	@JsonProperty("Updated")
	public String updated;
	@JsonProperty("Value")
	public String value;
	
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
