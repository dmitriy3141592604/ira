package utils;

import static org.junit.Assert.assertEquals;
import static utils.Triple.newTriple;

import org.junit.Test;

import testutils.RandomizedTest;

public class ByFstTripleComparatorTest implements RandomizedTest {

	@Test
	public void test$compareByFirst() {
		final ByFstTripleComparator cp = ByFstTripleComparator.byFstComparatorInstance();
		final Triple<Character, String, String> a = newTriple('a', randomString(), randomString());
		final Triple<Character, String, String> z = newTriple('z', randomString(), randomString());

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("");
		stringBuilder.append(cp.compare(a, z));
		stringBuilder.append(" ");
		stringBuilder.append(cp.compare(z, a));
		stringBuilder.append(" ");
		stringBuilder.append(cp.compare(a, a));
		stringBuilder.append(" ");
		stringBuilder.append(cp.compare(z, z));

		assertEquals("-25 25 0 0", stringBuilder.toString());
	}

}
