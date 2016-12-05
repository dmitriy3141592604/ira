package logic;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class SimpleConditionTestBase implements RandomizedTest {

	@Before
	public final void setUpSimpleConditionTestBase() {

	}

	protected SimpleCondition newSimpleCondition(String name, boolean value) {
		return new SimpleCondition(name, value);
	}

	protected SimpleCondition newSimpleCondition(String name) {
		return new SimpleCondition(name);
	}
}