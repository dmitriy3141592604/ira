package utils;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ ANNOTATION_TYPE, TYPE, METHOD, FIELD, CONSTRUCTOR })
@Responsibility("Фиксирует зону ответственности аннотируемой сущьности")
public @interface Responsibility {

	@Responsibility("Предоставляет описание зоны отвественности аннотированной сущьности")
	String value();

}
