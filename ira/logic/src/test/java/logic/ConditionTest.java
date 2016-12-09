package logic;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import utils.Value;

public class ConditionTest {

	private String name;

	private boolean value;

	private Condition condition;

	@Before
	public final void setUpConditionTestBase() {
		condition = new Condition() {

			@Override
			public boolean getValue(Optional<StringBuilder> OLog) {
				return value;
			}

			@Override
			public String getName() {
				return name;
			}

		};
	}

	@Test
	public void test$conditionalRunRunnable$called() {
		value = true;
		final Value<String> responseHolder = new Value<String>();
		condition.run(() -> responseHolder.setValue("asdf"));

		assertEquals("asdf", responseHolder.getValue());
	}

	@Test
	public void test$conditionalRunRunnable$notCalled() {
		value = false;
		final Value<String> responseHolder = new Value<String>();
		condition.run(() -> responseHolder.setValue("asdf"));

		assertEquals(null, responseHolder.getValue());
	}

}
