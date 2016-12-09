package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogicOperatorOrTest extends LogicOperatorTestBase {

	@Before
	public final void setUpLogicOperatorOrTest() {
		operator = new LogicOperatorOr();
	}

	@Test
	public void test$true() {
		foo.setValue(true);
		assertEquals(true, operator.eval(osb, foo));
	}

	@Test
	public void test$false() {
		foo.setValue(false);
		assertEquals(false, operator.eval(osb, foo));
	}

	@Test
	public void test$andContract() {
		assertEquals("true=true|false=true|false=true|false=false|", checkLogicTable((t, u) -> t && u));
	}

	@Test
	public void test$calculationIsLazy() {
		foo.setValue(true);
		assertEquals("true|or(foo:true)", withLog(foo, excepton));
	}

	@Test
	public void test$commaSeparated() {
		foo.setValue(false);
		bar.setValue(true);
		assertEquals("true|or(foo:false,bar:true)", withLog(foo, bar));
	}

}
