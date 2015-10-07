package sb.helpers;

public enum ETypeHelper {
	PRESENCE("P"),
	TEMPERATURE("T"),
	LUMINOSITY("L"),
	HUMIDITY("H"),
	IO("F"),
	SHUTTER("V"),
	LIGHT("L"),
	HEATING("C"),
	HLT("HLT"),
	NONE("N");
	
	ETypeHelper(String code){
		this._code = code;
	}
	
	@Override
	public String toString() {
		return _code;
	}
	
	private final String _code;
}
