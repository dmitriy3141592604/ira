package testutils;

import static org.junit.Assert.assertEquals;

import java.util.function.Supplier;

public abstract class MutableAssert {

	public static <MutableValue, Action extends Runnable> void assertMutation(MutableValue from, MutableValue to, Supplier<MutableValue> mutableValue,
			Action action) {
		if (from == null && to == null) {
			throw new AssertionError("from and to is null");
		}

		if (from != null && to != null && from.equals(to)) {
			throw new AssertionError("from equals to");
		}

		assertEquals(from, mutableValue.get());

		action.run();

		assertEquals(to, mutableValue.get());

	}
}