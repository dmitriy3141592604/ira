package utils.assertions;

public class NotNullGuard {

	public static <T> T guard(T value, String pattern, Object owner, Object child) {

		if (pattern == null) {
			throw new IllegalArgumentException("null pattern not allowed");
		}
		if (value == null) {
			throw new IllegalStateException(String.format(pattern, owner, child));
		}
		return value;
	}

}