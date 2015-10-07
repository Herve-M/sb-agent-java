package sb.jsonapi;

///Get_AllTypes
public enum ENetType {
	PRESENCE("5"),
	TEMPERATURE("2"),
	LUMINOSITY("4"),
	HUMIDITY("3"),
	IO("1"),
	SHUTTER("7"),
	LIGHT("6"),
	HEATING("8");
	
	ENetType(String code){
		this._code = code;
	}
	
	@Override
	public String toString() {
		return _code;
	}
	
	private final String _code;
}
