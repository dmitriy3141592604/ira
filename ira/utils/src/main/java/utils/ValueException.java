package utils;

public abstract class ValueException extends RuntimeException {

	private static final long serialVersionUID = 7848970903864430751L;

	public ValueException(String message) {
		super(message);
	}
}