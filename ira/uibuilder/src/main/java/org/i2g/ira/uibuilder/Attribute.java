package org.i2g.ira.uibuilder;

/**
 * Простое представление для Html аттрибутов
 *
 */
public interface Attribute {

	/**
	 * @return Имя html аттрибута
	 */
	String getName();

	/**
	 * Возвращает значение html аттрибута.
	 *
	 * Если возвращается null, то подразумевается, что это аттрибут без значения. Как к примеру аттрибут {@code selected} в теге {@code option}
	 *
	 * @return Значение html аттрибута.
	 */
	String getValue();
}