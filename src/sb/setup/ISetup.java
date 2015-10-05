package sb.setup;

public interface ISetup {
	void 	setArgs(String[] args);
	boolean setup();
	String 	getDescription();
	String 	getName();
}
