package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionCombinedTest extends ConditionCombinedTestBase {

	@Test
	public void test$constructor() {
		final String randomString = randomString();
		final ConditionCombined cc = newCombinedCondition(randomString, and, foo);
		assertEquals(randomString, cc.getName());
	}

	@Test
	public void test$getValue$true() {
		final String randomString = randomString();
		foo.setValue(true);
		final ConditionCombined cc = newCombinedCondition(randomString, and, foo);
		assertEquals("true|" + randomString + "[and(foo:true)]", withLog(cc));
	}

	@Test
	public void test$getValue$false() {
		final String randomString = randomString();
		foo.setValue(false);
		final ConditionCombined cc = newCombinedCondition(randomString, and, foo);
		assertEquals("false|" + randomString + "[and(foo:false)]", withLog(cc));
	}

}
