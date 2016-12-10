package utils;

public class OnceAssignmentValue<T> extends Value<T> {

	private int assignmentCounter = 0;

	protected OnceAssignmentValue() {
		super(null);
	}

	@Override
	public <U extends T> U setValue(U value) {
		if (assignmentCounter++ > 0) {
			throw new OnceAssignmentValueException(getValue(), value);
		}
		return super.setValue(value);
	}

}