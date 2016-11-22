package utils;

@SuppressWarnings("unchecked")
public abstract class IraProcessorsContextHolder {

	private static final ThreadLocal<Object> value = new ThreadLocal<Object>();

	public static void setValue(Object object) {
		value.set(object);
	}

	public static <T> T getValue() {
		return (T) value.get();
	}
}
