package utils;

import static java.util.Arrays.asList;

import java.util.Iterator;

public class JoinUtils {

	public static String join(String separator, Iterable<?> items) {
		return join(separator, items, new StringBuilder()).toString();
	}

	@SafeVarargs
	public static <T> String join(String separator, T... values) {
		return join(separator, asList(values));
	}

	private static StringBuilder join(String separator, Iterable<?> items, StringBuilder result) {
		final Iterator<?> iterator = items.iterator();

		if (iterator.hasNext()) {
			result.append(iterator.next());
		}

		while (iterator.hasNext()) {
			result.append(separator).append(iterator.next());
		}

		return result;
	}

}
