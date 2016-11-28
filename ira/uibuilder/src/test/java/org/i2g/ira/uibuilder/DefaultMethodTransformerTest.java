package org.i2g.ira.uibuilder;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import test.uibuilder.DefaultMethodTransformer;
import testutils.RandomizedTest;

public class DefaultMethodTransformerTest implements RandomizedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void test$StringArgument() throws Exception {
		final DefaultMethodTransformer t = new DefaultMethodTransformer();
		final String rs = randomString();
		final Method toStringMethod = String.class.getMethod("toString");
		final Tag tag = t.transform(toStringMethod, new Object[] { rs });
		assertEquals(rs, ((TextElement) tag).getText());
	}

	@Test
	public void test$unsupportedArgumet() throws Exception {
		exception.expect(IllegalArgumentException.class);

		final DefaultMethodTransformer t = new DefaultMethodTransformer();
		final Method toStringMethod = String.class.getMethod("toString");

		t.transform(toStringMethod, new Object[] { new Runnable() {
			@Override
			public void run() {
			}
		} });

	}

}
