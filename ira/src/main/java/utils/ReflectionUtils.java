package utils;

import java.lang.annotation.Annotation;

public class ReflectionUtils {

	public static boolean isAnnotationPresent(Class<?> c, Class<? extends Annotation> annotationClass) {
		return c.isAnnotationPresent(annotationClass);
	}

}
