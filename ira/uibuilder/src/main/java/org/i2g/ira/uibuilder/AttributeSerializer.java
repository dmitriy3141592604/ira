package org.i2g.ira.uibuilder;

import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;
import static utils.ByFstTripleComparator.byFstComparatorInstance;
import static utils.Triple.newTriple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import utils.ByFstTripleComparator;
import utils.Responsibility;
import utils.Triple;

@Responsibility("Обеспечивает сериализацию модели html аттрибута")
public class AttributeSerializer {

	private static final Pattern ALLOWED_TAG_NAME_PATTERN = Pattern.compile("^[-_0-9a-zA-Z]+$");

	private static final char SINGLE_QUOTE_CHAR = '\'';

	private static final char DOUBLE_QUOTE_CHAR = '"';

	/**
	 * Сериализует определение html аттрибута в строку, начинающуюся с пробела
	 * (для облегчения вывода в поток)
	 *
	 * @param attribute
	 * @return строка, в которой символы в значении аттрибута экранированы
	 */
	public String serialize(Attribute attribute, StringBuilder sb) {

		final String aName = attribute.getName();

		if (aName == null || !ALLOWED_TAG_NAME_PATTERN.matcher(aName).matches()) {
			final String s = "Имя должно быт не пустым значением и содержать только буквы, цифры, подчеркивания и знак дефиса.";
			throw new IllegalArgumentException(s);
		}

		sb.append(" ");
		sb.append(aName);

		final String aValue = attribute.getValue();
		if (null == aValue) {
			return sb.toString();
		}

		sb.append("=");
		protect(aValue, sb);
		return sb.toString();
	}

	public static class CharProtectionSymbolsBuilder {

		char[] protectableCharsList;
		String[] noProtectablesingleQuoteCharsList;
		String[] noProtectableDoubleQuoteCharsList;
		private final ByFstTripleComparator byFstComparator = byFstComparatorInstance();

		private final List<Triple<Character, String, String>> tmpArray = new ArrayList<Triple<Character, String, String>>();

		{
			registerMapping('\'', "'", "&39;");
			registerMapping('"', "&quot;", "\"");
			registerMapping('<', "&lt;", "&lt;");
			registerMapping('>', "&gt;", "&gt;");
			registerMapping('&', "&amp;", "&amp;");

			@SuppressWarnings("unchecked")
			final Triple<Character, String, String>[] sortedArray = tmpArray.toArray(new Triple[0]);

			sort(sortedArray, byFstComparator);

			protectableCharsList = new char[sortedArray.length];
			noProtectablesingleQuoteCharsList = new String[sortedArray.length];
			noProtectableDoubleQuoteCharsList = new String[sortedArray.length];

			for (int i = 0; i < sortedArray.length; ++i) {
				final Triple<Character, String, String> triple = sortedArray[i];
				protectableCharsList[i] = triple.getFst();
				noProtectablesingleQuoteCharsList[i] = triple.getSnd();
				noProtectableDoubleQuoteCharsList[i] = triple.getThird();
			}
		}

		private void registerMapping(char c, String string, String string2) {
			tmpArray.add(newTriple(c, string, string2));
		}

	}

	private final CharProtectionSymbolsBuilder precompiledCharacterMapping = new CharProtectionSymbolsBuilder();

	private void protect(CharSequence value, StringBuilder sb) {
		final char[] protectableChars = precompiledCharacterMapping.protectableCharsList;
		final String[] noProtectSingleQuote = precompiledCharacterMapping.noProtectablesingleQuoteCharsList;
		final String[] noProtectDoubleQuote = precompiledCharacterMapping.noProtectableDoubleQuoteCharsList;

		String[] protectResult = noProtectSingleQuote;

		final StringBuilder out = sb;
		boolean hasSingleQuote = false;
		boolean hasDoubleQuote = false;

		for (int i = 0; i < value.length() && (!hasSingleQuote && !hasDoubleQuote); ++i) {
			final char c = value.charAt(i);
			hasSingleQuote |= c == SINGLE_QUOTE_CHAR;
			hasDoubleQuote |= c == DOUBLE_QUOTE_CHAR;
		}

		char prefferedQuote = DOUBLE_QUOTE_CHAR;
		protectResult = noProtectSingleQuote;
		if (!hasDoubleQuote) {
			prefferedQuote = DOUBLE_QUOTE_CHAR;
			protectResult = noProtectSingleQuote;
		} else if (!hasSingleQuote) {
			prefferedQuote = SINGLE_QUOTE_CHAR;
			protectResult = noProtectDoubleQuote;
		}
		sb.append(prefferedQuote);
		final char maxChar = protectableChars[protectableChars.length - 1];
		for (int i = 0; i < value.length(); ++i) {
			calculateOutString(value.charAt(i), protectableChars, protectResult, maxChar, out);
		}
		sb.append(prefferedQuote);
	}

	private void calculateOutString(final char c, final char[] protectableChars, String[] protectResult, char maxChar, StringBuilder out) {
		if (c > maxChar) {
			out.append(c);
			return;
		}
		final int idx = binarySearch(protectableChars, c);
		if (idx >= 0) {
			out.append(protectResult[idx]);
			return;
		}
		out.append(c);
	}

	public String serialize(Attribute attribute) {
		final StringBuilder sb = new StringBuilder();
		serialize(attribute, sb);
		return sb.toString();
	}

	public void serialize(Collection<Attribute> attributes, StringBuilder sb) {
		attributes.forEach(a -> serialize(a, sb));
	}

}
