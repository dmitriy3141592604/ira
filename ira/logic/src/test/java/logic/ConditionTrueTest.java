package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionTrueTest {
	@Test
	public void test$name() {
		assertEquals("const(true)", new ConditionTrue().getName());
	}

	@Test
	public void test$value() {
		assertEquals(true, new ConditionTrue().getValue());
	}
}
