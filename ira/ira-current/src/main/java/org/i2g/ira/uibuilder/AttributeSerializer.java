package org.i2g.ira.uibuilder;

import java.util.Collection;

/**
 * Выполняет сериализацию модели html аттрибута {@linkplain Attribute}
 * TODO Добавить возможность использовать внешний StringBuilder
 */
public class AttributeSerializer {

	/**
	 * Сериализует определение html аттрибута в строку, начинающуюся с пробела (для облегчения вывода в поток)
	 *
	 * @param attribute
	 * @return строка, в которой символы в значении аттрибута экранированы
	 */
	public String serialize(Attribute attribute, StringBuilder sb) {

		final String aName = attribute.getName();
		final String aValue = attribute.getValue();

		if (aName == null || !aName.matches("^[-_0-9a-zA-Z]+$")) {
			final String s = "Имя должно быт не пустым значением и содержать только буквы, цифры, подчеркивания и знак дефиса.";
			throw new IllegalArgumentException(s);
		}

		sb.append(" ");
		sb.append(aName);

		if (null == aValue) {
			return sb.toString();
		}

		final ProtectResult protectResult = protect(aValue);
		sb.append("=");
		sb.append(protectResult.getPrefferedQuote());
		sb.append(protectResult.getProtectedValue());
		sb.append(protectResult.getPrefferedQuote());
		return sb.toString();
	}

	private ProtectResult protect(CharSequence value) {
		final char[] protectableChars = new char[] { '\'', '"', '<', '>', '&' };
		final String[] noProtectSingleQuote = new String[] { "'", "&quot;", "&lt;", "&gt;", "&amp;" };
		final String[] noProtectDoubleQuote = new String[] { "&#39;", "\"", "&lt;", "&gt;", "&amp;" };
		String[] protectResult = noProtectSingleQuote;

		final StringBuilder out = new StringBuilder();
		boolean hasSingleQuote = false;
		boolean hasDoubleQuote = false;

		// TODO Нет необходимости проходить весь массив. Как только включится режим mixed можно заканчивать
		for (int i = 0; i < value.length(); ++i) {
			final char c = value.charAt(i);
			hasSingleQuote |= c == '\'';
			hasDoubleQuote |= c == '"';
		}

		char prefferedQuote = '"';
		protectResult = noProtectSingleQuote;
		if (!hasDoubleQuote) {
			prefferedQuote = '\"';
			protectResult = noProtectSingleQuote;
		} else if (!hasSingleQuote) {
			prefferedQuote = '\'';
			protectResult = noProtectDoubleQuote;
		}

		for (int i = 0; i < value.length(); ++i) {
			final char c = value.charAt(i);
			String outString = String.valueOf(c);
			// TODO Лучше бы использовать бинарый поиск
			for (int j = 0; j < protectableChars.length; ++j) {
				if (c == protectableChars[j]) {
					outString = protectResult[j];
				}
			}
			out.append(outString);
		}

		return new ProtectResult(out.toString(), prefferedQuote);
	}

	/**
	 * Результат обработки входной строки в которой необходимо экранировать символы
	 *
	 */
	private static class ProtectResult {

		private final CharSequence protectedValue;

		private final Character prefferedQuote;

		public ProtectResult(CharSequence protectedValue, Character prefferedQuote) {
			this.protectedValue = protectedValue;
			this.prefferedQuote = prefferedQuote;
		}

		/**
		 * @return Результирующая строка с экранированными символами
		 */
		public CharSequence getProtectedValue() {
			return protectedValue;
		}

		/**
		 *
		 * @return Результат с рекомендуемым символом ограничителя для строки
		 */
		public Character getPrefferedQuote() {
			return prefferedQuote;
		}

	}

	public String serialize(Attribute attribute) {
		final StringBuilder sb = new StringBuilder();
		serialize(attribute, sb);
		return sb.toString();
	}

	public String serialize(Collection<Attribute> attributes, StringBuilder sb) {
		attributes.forEach(a -> serialize(a, sb));
		return sb.toString();
	}

}
