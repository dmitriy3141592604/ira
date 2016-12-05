package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CombinedConditionTest extends CombinedConditionTestBase {

	@Test
	public void test$constructor() {
		final String randomString = randomString();
		final CombinedCondition cc = newCombinedCondition(randomString, and, foo);
		assertEquals(randomString, cc.getName());
	}

	@Test
	public void test$getValue$true() {
		final String randomString = randomString();
		foo.setValue(true);
		final CombinedCondition cc = newCombinedCondition(randomString, and, foo);
		assertEquals("true|" + randomString + "[and(foo:true)]", withLog(cc));
	}

	private String withLog(CombinedCondition cc) {
		final StringBuilder evalLog = new StringBuilder();
		final StringBuilder log = new StringBuilder();
		final boolean value = cc.getValue(evalLog);
		log.append(value);
		log.append("|");
		log.append(evalLog);
		return log.toString();
	}

	@Test
	public void test$getValue$false() {
		final String randomString = randomString();
		foo.setValue(false);
		final CombinedCondition cc = newCombinedCondition(randomString, and, foo);
		assertEquals("false|" + randomString + "[and(foo:false)]", withLog(cc));
	}

}
