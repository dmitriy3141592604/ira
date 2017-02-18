package utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Пометка, что класс содержит модель диаграммы
 * 
 * @author fdv.741
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
public @interface Diagramm {

}
