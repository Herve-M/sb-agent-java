package sb.jsonapi;

public enum ENetAssembly {
	Bool("System.Boolean, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089"),
	Int32("System.Int32, mscorlib, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089");
	
	ENetAssembly(String code){
		this._code = code;
	}
	
	private final String _code;
}
