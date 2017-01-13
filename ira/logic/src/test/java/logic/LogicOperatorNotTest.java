package logic;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LogicOperatorNotTest extends LogicOperatorNotTestBase {

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
		not.eval(empty());
	}

	@Test
	public void test$moreThatOneArgument() {
		exception.expect(LogicOperatorNotException.class);
		not.eval(empty(), new ConditionTrue(), new ConditionTrue());
	}

	@Test
	public void test$nullInputParameter() throws Exception {
		exception.expect(IllegalArgumentException.class);
		getEvalMethod().invoke(not, empty());
	}

}
