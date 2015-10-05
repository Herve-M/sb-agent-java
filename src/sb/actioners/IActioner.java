package sb.actioners;

public interface IActioner {
	String 		getName();
	String 		getTypeCode();
	boolean		getState();
	int 		getValue();
	boolean 	setValue(int value);
}
