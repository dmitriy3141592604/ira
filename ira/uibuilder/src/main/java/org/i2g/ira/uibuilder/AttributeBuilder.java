package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeHelper.newAttribute;

/**
 * Обеспечивает поддержку формирования аттрибутов на основе имени вызывающей функции
 *
 * @author fdv.741
 *
 */
public class AttributeBuilder {

	/**
	 * Создает атрибут с именем вызывющего метода
	 *
	 * @param value
	 *            - значение аттрибута
	 * @return
	 */
	@SafeVarargs
	public static <T> Attribute asAttribute(T... value) {
		final Exception e = new Exception();

		final StackTraceElement element = e.getStackTrace()[1];
		final String methodName = element.getMethodName();

		return newAttribute(methodName, joinValues(value));
	}

	@SafeVarargs
	public static <T> Attribute asNamedAttribute(String name, T... values) {
		if (values.length == 0) {
			return newAttribute(name);
		}
		return newAttribute(name, joinValues(values));
	}

	public static Attribute asEmptyAttribute() {
		return newAttribute(new Exception().getStackTrace()[1].getMethodName());
	}

	@SafeVarargs
	// XXX При замене на JoinUtils куча ошибок. Нужно починить
	private static <T> String joinValues(T... values) {
		final StringBuilder value = new StringBuilder();
		for (final T v : values) {
			value.append(" ");
			value.append(v);
		}
		return value.toString().substring(1);
	}

	// XXX Желателен кэш, что бы уменьшить время поиска
}
