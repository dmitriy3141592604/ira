package utils;

public class BuiltinArraysUtils {
	private static final String BUILTIN_CLASS_NAME_PREFIX = "class [";

	public static boolean isBuiltinArray(Object object) {
		return object.getClass().toString().startsWith(BUILTIN_CLASS_NAME_PREFIX);
	}
}