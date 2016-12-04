package logiс.operators;

import org.junit.Before;

import logic.combinators.SimpleCondition;

public abstract class OperatorTestBase {

	protected SimpleCondition right;
	protected SimpleCondition left;
	private StringBuilder log;

	@Before
	public final void setUp() {
		right = new SimpleCondition("right");
		left = new SimpleCondition("left");
		log = new StringBuilder();
	}

	protected String join() {
		return And.and.combine(log, left, right) + "|" + log.toString();
	}

}
