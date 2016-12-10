package utils;

import java.util.Arrays;
import java.util.Iterator;

public class JoinUtils {

	public static String join(String separator, Iterable<?> items) {
		final StringBuilder result = new StringBuilder();
		final Iterator<?> iterator = items.iterator();

		if (iterator.hasNext()) {
			result.append(iterator.next());
		}

		while (iterator.hasNext()) {
			result.append(separator).append(iterator.next());
		}

		return result.toString();
	}

	public static <T> String join(String separator, T[] values) {
		return join(separator, Arrays.asList(values));
	}

}
