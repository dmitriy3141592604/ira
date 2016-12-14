package logic;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class ConditionSimpleTestBase implements RandomizedTest {

	@Before
	public final void setUpConditionSimpleTestBase() {

	}

	protected ConditionSimple newCondition(String name, boolean value) {
		return new ConditionSimple(name, value);
	}

	protected ConditionSimple newSimpleCondition(String name) {
		return new ConditionSimple(name);
	}

	protected ConditionSimple newCondition() {
		return newCondition(randomString(), randomBoolean());
	}

}