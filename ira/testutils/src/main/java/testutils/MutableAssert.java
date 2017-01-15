package testutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.function.Supplier;

public class MutableAssert {

	public static <MutableValue, Action extends Runnable> void assertMutation(MutableValue from, MutableValue to, Supplier<MutableValue> mutableValue,
			Action action) {
		if (from == null && to == null) {
			fail("from and to is null");
		}

		if (from != null && to != null && from.equals(to)) {
			fail("from equals to");
		}

		assertEquals(from, mutableValue.get());

		action.run();

		assertEquals(to, mutableValue.get());

	}
}