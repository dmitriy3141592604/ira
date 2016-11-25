package utils;

import static org.junit.Assert.*;
import static utils.Tuple.newTuple;

import org.junit.Test;

import testutils.RandomizedTest;

public class TupleTest extends IraTest implements RandomizedTest {

	@Test
	public void test$fst() {
		String fst = randomString();
		assertEquals(fst, newTuple(fst, "snd").getFst());
	}

	@Test
	public void test$snd() {
		String snd = randomString();
		assertEquals(snd, newTuple(randomString(), snd).getSnd());

	}

}
