package logic;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionSimpleTest extends ConditionSimpleTestBase {

	@Test
	public void test$value() {
		final boolean randomValue = randomBoolean();
		assertEquals(randomValue, newCondition(randomString(), randomValue).getValue(empty()));
	}

	@Test
	public void test$setValue() {
		final ConditionSimple condition = newCondition();

		final boolean value = randomBoolean();

		condition.setValue(value);

		assertEquals(value, condition.getValue(empty()));
	}

	@Test
	public void test$name() {
		final String name = randomString();
		assertEquals(name, newSimpleCondition(name).getName());
	}

	@Test
	public void test$setOn() {
		final ConditionSimple c = newCondition();
		c.setValue(false);
		final boolean previousValue = c.getValue();
		c.setOn();
		final boolean newValue = c.getValue();
		assertEquals("false|true", previousValue + "|" + newValue);

	}

	@Test
	public void test$setOff() {
		final ConditionSimple c = newCondition();
		c.setValue(true);
		final boolean previousValue = c.getValue();
		c.setOff();
		final boolean newValue = c.getValue();
		assertEquals("true|false", previousValue + "|" + newValue);

	}

}
