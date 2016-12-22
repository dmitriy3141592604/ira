package utils;

public class ReflectionUtils {

	public static boolean isAnnotationPresent(Class<?> c, Class<?> annotationClass) {
		return annotationClass.isAssignableFrom(c);
	}

}
