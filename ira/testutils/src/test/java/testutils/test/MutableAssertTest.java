package testutils.test;

import static org.junit.Assert.fail;
import static testutils.MutableAssert.assertMutation;

import org.junit.Test;

import testutils.MutableAssertTestBase;

public class MutableAssertTest extends MutableAssertTestBase {

	@Test
	public void test$expectedContractBehavior$correct() {
		final StringBuilder sb = new StringBuilder();
		assertMutation("", "called", () -> sb.toString(), () -> sb.append("called"));
	}

	@Test
	public void test$expectedContractBehavior$notCorrect() {
		try {
			final StringBuilder sb = new StringBuilder();
			assertMutation("", "noCalled", () -> sb.toString(), () -> sb.append("called"));
		} catch (final Error error) {
			return;
		}
		fail("");
	}

	@Test
	public void test$toAndFromShouldBeNotEqual() {
		try {
			final StringBuilder sb = new StringBuilder();
			assertMutation(1, 1, () -> 1, () -> sb.append("called"));
		} catch (final Error error) {
			return;
		}
		fail("Должен падать, если from и to равны");
	}

	@Test
	public void test$toAndFromShouldBeNotEqual$bothNullCase() {
		try {
			final StringBuilder sb = new StringBuilder();
			assertMutation(null, null, () -> null, () -> sb.append("called"));
		} catch (final Error error) {
			return;
		}
		fail("Должен падать, если from и to равны");
	}

	@Test
	public void test$mutableFromLeftNullToValueIsCorrect() {
		assertMutation(null, "value", () -> value, () -> value = "value");
	}

	@Test
	public void test$mutableToNullIsCorrect() {
		value = "value";
		assertMutation("value", null, () -> value, () -> value = null);
	}
}
