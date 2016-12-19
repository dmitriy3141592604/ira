package application;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import utils.Responsibility;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Responsibility("Предоставляет интерфейс связывания формы и контроллера")
public @interface FormControllerName {

	Class<?> value();
}
