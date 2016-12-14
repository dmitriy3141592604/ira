package logic;

import static java.util.Optional.of;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class LogicOperatorNotTestBase implements RandomizedTest {

	protected LogicOperatorNot not;
	protected StringBuilder sb;

	@Before
	public final void setUpLogicOperatorNotTestBase() {
		not = new LogicOperatorNot();
		sb = new StringBuilder();
	}

	protected String apply(boolean actual) {
		final boolean retVal = not.eval(of(sb), new ConditionSimple("foo", actual));

		return retVal + "|" + sb.toString();
	}

	protected ConditionSimple randomCondition() {
		return new ConditionSimple(randomString(), randomBoolean());
	}
}
