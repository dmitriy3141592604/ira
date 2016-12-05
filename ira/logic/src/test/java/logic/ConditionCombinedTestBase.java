package logic;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class ConditionCombinedTestBase implements RandomizedTest {

	protected ConditionSimple foo;

	protected ConditionSimple barCondition;

	protected LogicOperatorAnd and;

	@Before
	public final void setUpCombinedConditionTestBase() {
		foo = new ConditionSimple("foo");
		barCondition = new ConditionSimple("bar");
		and = new LogicOperatorAnd();

	}

	protected ConditionCombined newCombinedCondition(String randomString, LogicOperator operator, Condition... conditions) {
		return new ConditionCombined(randomString, operator, conditions);
	}

	protected String withLog(ConditionCombined cc) {
		final StringBuilder evalLog = new StringBuilder();
		final StringBuilder log = new StringBuilder();
		final boolean value = cc.getValue(evalLog);
		log.append(value);
		log.append("|");
		log.append(evalLog);
		return log.toString();
	}
}
