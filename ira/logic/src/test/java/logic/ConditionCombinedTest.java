package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionCombinedTest extends ConditionCombinedTestBase {

	@Test
	public void test$constructor() {
		final ConditionCombined cc = newCombinedCondition(rs, and, foo);
		assertEquals(rs, cc.getName());
	}

	@Test
	public void test$getValue$true() {
		foo.setValue(true);
		final ConditionCombined cc = newCombinedCondition(rs, and, foo);
		assertEquals("true|" + rs + "[and(foo:true)]", withLog(cc));
	}

	@Test
	public void test$getValue$false() {
		foo.setValue(false);
		final ConditionCombined cc = newCombinedCondition(rs, and, foo);
		assertEquals("false|" + rs + "[and(foo:false)]", withLog(cc));
	}

}
