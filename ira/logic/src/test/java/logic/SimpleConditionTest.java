package logic;

import org.junit.Assert;
import org.junit.Test;

import testutils.RandomizedTest;

public class SimpleConditionTest extends Assert implements RandomizedTest {

	@Test
	public void test$value() {
		final boolean value = randomBoolean();
		assertEquals(value, new SimpleCondition(randomString(), value).getValue(null));
	}

	@Test
	public void test$setValue() {
		final SimpleCondition simpleCondition = new SimpleCondition(randomString(), randomBoolean());
		final boolean value = randomBoolean();
		simpleCondition.setValue(value);
		assertEquals(value, simpleCondition.getValue(null));
	}

	@Test
	public void test$name() {
		final String name = randomString();
		assertEquals(name, new SimpleCondition(name).getName());
	}

}
