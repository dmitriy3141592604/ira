package logic;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class CombinedConditionTestBase implements RandomizedTest {

	protected SimpleCondition foo;

	protected SimpleCondition barCondition;

	protected LogicOperatorAnd and;

	@Before
	public final void setUpCombinedConditionTestBase() {
		foo = new SimpleCondition("foo");
		barCondition = new SimpleCondition("bar");
		and = new LogicOperatorAnd();

	}

	protected CombinedCondition newCombinedCondition(String randomString, LogicOperator operator, Condition... conditions) {
		return new CombinedCondition(randomString, operator, conditions);
	}
}
