package sb.actioners;

public interface IActioner {
	String getName();
	boolean getState();
	int 	getValue();
	boolean setValue(int value);
}
