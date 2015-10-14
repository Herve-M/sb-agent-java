package sb.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JSTemperature {
	@JsonProperty("Id")
	public int Id;
	@JsonProperty("Average")
	public int Average;
	@JsonProperty("Needed")
	public int Needed;
	@JsonProperty("Min")
	public int Min;
	@JsonProperty("Max")
	public int Max;
	@JsonProperty("Ref")
	public int Ref;
	@JsonProperty("Manual")
	public boolean Manual;
}
