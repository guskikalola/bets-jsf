package exceptions;

public class PertsonaAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;

	public PertsonaAlreadyExists() {
		super();
	}
	
	public PertsonaAlreadyExists(String s) {
		super(s);
	}
}
