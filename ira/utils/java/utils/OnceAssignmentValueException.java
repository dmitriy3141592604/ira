package utils;

public class OnceAssignmentValueException extends ValueException {

	private static final long serialVersionUID = 2442823627552767230L;

	public OnceAssignmentValueException(Object priviousValue, Object newVale) {
		super("Second assigment not allowed. Currently stored: " + priviousValue + " and will append " + newVale);
	}

}