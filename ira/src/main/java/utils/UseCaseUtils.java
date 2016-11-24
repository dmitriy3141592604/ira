package utils;

public class UseCaseUtils {

	public static boolean hasUserCaseAnnotation(Class<?> t) {
		return ReflectionUtils.isAnnotationPresent(t, UseCase.class);
	}

}
