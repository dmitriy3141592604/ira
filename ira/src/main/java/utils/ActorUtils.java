package utils;

/**
 * F_IXME Тут можно проверить покрытие тестами невызываемого дефолтного
 * конструктора
 *
 * Result: Специальный тест с вызовом дефолтного конструктора обеспечил полное
 * покрытие.
 *
 */
public abstract class ActorUtils {

	public static boolean hasActorAnnotation(Class<?> c) {
		return ReflectionUtils.isAnnotationPresent(c, Actor.class);
	}

}
