package logic;

import java.util.function.BiPredicate;

import org.junit.Before;
import org.junit.Test;

public abstract class LogicOperatorTestBase {

	protected LogicOperator operator;
	protected SimpleCondition foo;
	protected SimpleCondition bar;
	protected ConditionBase excepton;
	protected StringBuilder sb;

	public static class LogicOperatorTestException extends RuntimeException {
		private static final long serialVersionUID = 264023030316091260L;
	}

	@Before
	public final void LogicOperatorAndTestBase() {
		sb = new StringBuilder();
		foo = new SimpleCondition("foo");
		bar = new SimpleCondition("bar");
		excepton = new ConditionBase("exception") {

			@Override
			public boolean getValue(StringBuilder log) {
				throw new UnsupportedOperationException();
			}

		};

	}

	@Test(expected = IllegalArgumentException.class)
	public void test$emptyListNotAllowe() {
		operator.eval(sb);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test$nullListNotAllowe() {
		operator.eval(sb, (Condition[]) null);
	}

	protected String checkLogicTable(BiPredicate<Boolean, Boolean> bc) {
		final StringBuilder testLog = new StringBuilder();
		final boolean[] items = new boolean[] { true, false };
		for (int i = 0; i < items.length; ++i) {
			for (int j = 0; j < items.length; ++j) {

				foo.setValue(items[i]);
				bar.setValue(items[j]);
				testLog.append(bc.test(items[i], items[j]));
				testLog.append("=");
				testLog.append(operator.eval(sb, foo, bar));
				testLog.append("|");
			}
		}
		return testLog.toString();
	}

	protected String withLog(Condition... conditions) {
		final boolean value = operator.eval(sb, conditions);

		final StringBuilder log = new StringBuilder();
		log.append(value);
		log.append("|");
		log.append(sb);
		return log.toString();
	}

}