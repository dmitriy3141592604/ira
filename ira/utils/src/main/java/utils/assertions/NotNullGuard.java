package utils.assertions;

public class NotNullGuard {
	public static <A> A guard(A annotation, String pattern, Object owner, Object child) {

		if (pattern == null) {
			throw new IllegalArgumentException("null pattern not allowed");
		}
		if (annotation == null) {
			throw new IllegalStateException(String.format(pattern, owner, child));
		}
		return annotation;
	}

}