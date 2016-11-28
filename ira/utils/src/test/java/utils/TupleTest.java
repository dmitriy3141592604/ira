package utils;

import static org.junit.Assert.assertEquals;
import static utils.Tuple.newTuple;

import org.junit.Test;

import testutils.RandomizedTest;

public class TupleTest implements RandomizedTest {

	@Test
	public void test$fst() {
		final String fst = randomString();
		assertEquals(fst, newTuple(fst, "snd").getFst());
	}

	@Test
	public void test$snd() {
		final String snd = randomString();
		assertEquals(snd, newTuple(randomString(), snd).getSnd());

	}

}
