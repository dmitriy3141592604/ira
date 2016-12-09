package logic;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionSimpleTest extends ConditionSimpleTestBase {

	@Test
	public void test$value() {
		final boolean randomValue = randomBoolean();
		assertEquals(randomValue, newSimpleCondition(randomString(), randomValue).getValue(empty()));
	}

	@Test
	public void test$setValue() {
		final ConditionSimple simpleCondition = new ConditionSimple(randomString(), randomBoolean());

		final boolean value = randomBoolean();

		simpleCondition.setValue(value);

		assertEquals(value, simpleCondition.getValue(empty()));
	}

	@Test
	public void test$name() {
		final String name = randomString();
		assertEquals(name, newSimpleCondition(name).getName());
	}

}
