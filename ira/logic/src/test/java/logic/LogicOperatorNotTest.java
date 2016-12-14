package logic;

import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;
import static org.junit.rules.ExpectedException.none;

import java.lang.reflect.Method;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LogicOperatorNotTest extends LogicOperatorNotTestBase {

	@Rule
	public ExpectedException exception = none();

	@Test
	public void test$true() {
		assertEquals("false|not(foo:true)", apply(true));
	}

	@Test
	public void test$false() {
		assertEquals("true|not(foo:false)", apply(false));
	}

	@Test
	public void test$emptyInputArgumentsList() {
		exception.expect(LogicOperatorNotException.class);
		not.eval(of(new StringBuilder()));
	}

	@Test
	public void test$moreThatOneArgument() {
		exception.expect(LogicOperatorNotException.class);
		not.eval(of(new StringBuilder()), randomCondition(), randomCondition());
	}

	@Test
	public void test$nullInputParameter() throws Exception {
		exception.expect(IllegalArgumentException.class);
		final Method declaredMethod = getEvalMethod();
		declaredMethod.invoke(not, Optional.of(new StringBuilder()));
	}

	private Method getEvalMethod() throws NoSuchMethodException {

		final Class<LogicOperatorNot> class1 = LogicOperatorNot.class;
		for (final Method method : class1.getDeclaredMethods()) {
			if ("eval".equals(method.getName())) {
				return method;
			}
		}
		throw new RuntimeException("methd not found");
	}

}
