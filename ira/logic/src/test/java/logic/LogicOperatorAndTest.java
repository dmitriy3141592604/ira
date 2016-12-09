package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogicOperatorAndTest extends LogicOperatorTestBase {

	@Before
	public final void setUpLogicOperatorAndTest() {
		operator = new LogicOperatorAnd();
	}

	@Test
	public void test$Name() {
		assertEquals("and", operator.getName());
	}

	@Test
	public void test$true() {
		foo.setValue(true);
		assertEquals(true, operator.eval(osb, foo));
	}

	@Test
	public void test$faltse() {
		foo.setValue(false);
		assertEquals(false, operator.eval(osb, foo));
	}

	@Test
	public void test$andContract() {
		assertEquals("true=true|false=false|false=false|false=false|", checkLogicTable((t, u) -> t && u));
	}

	@Test
	public void test$calculationIsLazy() {
		assertEquals("false|and(foo:false)", withLog(foo, excepton));
	}

	@Test
	public void test$separateWithCommas() {
		foo.setValue(true);
		assertEquals("true|and(foo:true,foo:true)", withLog(foo, foo));
	}

}
