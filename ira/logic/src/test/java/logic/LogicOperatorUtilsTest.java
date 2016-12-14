package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogicOperatorUtilsTest implements LogicOperatorUtils {

	private ConditionSimple foo;

	@Before
	public void setUpLogicOperatorUtilsTest() {
		foo = booleanFlag("foo", true);
	}

	@Test
	public void test$and() {
		assertEquals(false, not("not", foo).getValue());
	}

}
