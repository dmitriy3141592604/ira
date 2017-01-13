package logic;

import testutils.RandomizedTest;

public abstract class ConditionSimpleTestBase implements RandomizedTest {

	protected ConditionSimple newCondition(String name, boolean value) {
		return new ConditionSimple(name, value);
	}

	protected ConditionSimple newSimpleCondition(String name) {
		return new ConditionSimple(name);
	}

	protected ConditionSimple newCondition(Boolean value) {
		return newCondition(randomString(), value);
	}

}