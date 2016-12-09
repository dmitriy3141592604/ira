package logic;

import static java.util.Optional.of;

import java.util.Optional;
import java.util.function.BiPredicate;

import org.junit.Before;
import org.junit.Test;

public abstract class LogicOperatorTestBase {

	protected LogicOperator operator;
	protected ConditionSimple foo;
	protected ConditionSimple bar;
	protected ConditionBase excepton;
	protected Optional<StringBuilder> osb;

	public static class LogicOperatorTestException extends RuntimeException {
		private static final long serialVersionUID = 264023030316091260L;
	}

	@Before
	public final void LogicOperatorAndTestBase() {
		osb = of(new StringBuilder());
		foo = new ConditionSimple("foo");
		bar = new ConditionSimple("bar");
		excepton = new ConditionBase("exception") {

			@Override
			public boolean getValue(Optional<StringBuilder> osb) {
				throw new UnsupportedOperationException();
			}

		};

	}

	@Test(expected = IllegalArgumentException.class)
	public void test$emptyListNotAllowe() {
		operator.eval(osb);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test$nullListNotAllowe() {
		operator.eval(osb, (Condition[]) null);
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
				testLog.append(operator.eval(osb, foo, bar));
				testLog.append("|");
			}
		}
		return testLog.toString();
	}

	protected String withLog(Condition... conditions) {
		final boolean value = operator.eval(osb, conditions);

		final StringBuilder log = new StringBuilder();
		log.append(value);
		log.append("|");
		// TODO Переделать
		log.append(osb.get().toString());
		return log.toString();
	}

}