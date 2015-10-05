package sb.helpers;

public enum ETypeHelper {
	PRESENCE("P"),
	TEMPERATURE("T"),
	LUMINOSITY("L"),
	HUMIDITY("H"),
	IO("F"),
	SHUTTER("V"),
	LIGHT("L"),
	HEATING("C");
	
	ETypeHelper(String code){
		this._code = code;
	}
	
	private final String _code;
}
