package utils;

import static org.junit.Assert.assertSame;

import org.junit.Test;

public class ValueTest {

	@Test
	public void test$constructor() {
		final Object o = new Object();
		assertSame(o, new Value<Object>(o).getValue());
	}

	@Test
	public void test$newValue() {
		final Object o = new Object();
		assertSame(o, Value.newValue(o).getValue());
	}

}
