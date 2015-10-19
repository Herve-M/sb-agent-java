package sb.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JSSMAState {
	@JsonProperty("Id")
	public int Id;
	@JsonProperty("Manual")
	public boolean Manual;
}
