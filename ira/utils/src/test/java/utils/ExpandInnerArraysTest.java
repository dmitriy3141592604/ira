package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpandInnerArraysTest {

	@Test
	public void test$expand() {
		final Object[] one = new Object[] { "one" };
		final Object[] two = new Object[] { "two" };
		final Object[] tree = new Object[] { "three" };
		final Object[] objects2 = new Object[] { one, two };
		final Object[] objects3 = new Object[] { objects2, tree };
		final StringBuilder log = new StringBuilder();
		for (final Object o : expand(objects3)) {
			log.append("|");
			log.append(o.toString());
		}
		assertEquals("|one|two|three", log.toString());
	}

	private Object[] expand(Object[] objects3) {
		return new ExpandInnerArray().expand(objects3);
	}

}
