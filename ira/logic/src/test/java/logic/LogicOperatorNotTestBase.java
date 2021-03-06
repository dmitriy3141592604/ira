package logic;

import static java.util.Optional.of;
import static org.junit.rules.ExpectedException.none;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;

public abstract class LogicOperatorNotTestBase implements RandomizedTest {

	protected LogicOperatorNot not;
	protected StringBuilder sb;
	@Rule
	public ExpectedException exception = none();

	@Before
	public final void setUpLogicOperatorNotTestBase() {
		not = new LogicOperatorNot();
		sb = new StringBuilder();
	}

	protected String apply(boolean actual) {
		final boolean retVal = not.eval(of(sb), new ConditionSimple("foo", actual));

		return retVal + "|" + sb.toString();
	}

	protected Method getEvalMethod() throws NoSuchMethodException {
		for (final Method method : LogicOperatorNot.class.getDeclaredMethods()) {
			if ("eval".equals(method.getName())) {
				return method;
			}
		}
		throw new RuntimeException("methd not found");
	}
}
