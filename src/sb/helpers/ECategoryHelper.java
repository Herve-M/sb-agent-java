package sb.helpers;

public enum ECategoryHelper {
	SENSOR("CAP"),
	ACTIONER("ACT"),
	AGENT("AGT"),
	CONTAINER("CTN");
	
	ECategoryHelper(String code){
		this._code = code;
	}
	
	@Override
	public String toString() {
		return  _code;
	}
	
	private final String _code;				
}
