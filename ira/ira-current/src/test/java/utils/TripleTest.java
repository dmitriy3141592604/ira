package utils;

import static org.junit.Assert.assertEquals;
import static utils.Triple.newTriple;

import org.junit.Test;

import testutils.RandomizedTest;

public class TripleTest implements RandomizedTest {

	@Test
	public void test$triple() {
		final String fst = randomString();
		final String snd = randomString();
		final String third = randomString();
		final Triple<String, String, String> t = newTriple(fst, snd, third);
		final String result = new StringBuilder().append(t.getFst()).append("|").append(t.getSnd()).append("|").append(t.getThird()).toString();
		assertEquals(fst + "|" + snd + "|" + third, result);
	}

}
