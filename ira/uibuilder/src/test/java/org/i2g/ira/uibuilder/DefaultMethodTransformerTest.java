package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeHelper.newAttribute;
import static org.junit.Assert.assertEquals;
import static utils.Safer.safe;

import java.lang.reflect.Method;

import org.junit.Test;

import testutils.RandomizedTest;

public class DefaultMethodTransformerTest extends DefaultMethodTransformerTestBase implements RandomizedTest {

	private final Method toStringMethod = safe(() -> String.class.getMethod("toString"));

	@Test
	public void test$StringArgument() throws Exception {

		final Tag tag = transform(toStringMethod, rs);

		assertEquals(rs, ((TextElement) tag).getText());
	}

	@Test
	public void test$unsupportedArgumet() throws Exception {

		exception.expect(IllegalArgumentException.class);

		transform(toStringMethod, runnable);

	}

	@Test
	public void test$argsIsNull() {
		final Tag transform = transform(toStringMethod);
		assertEquals("toString", ((Element) transform).getName());
	}

	@Test
	public void test$twoArgument() {
		final Tag transform = transform(toStringMethod, newAttribute("one"), newAttribute("two"));
		assertEquals("toString", ((Element) transform).getName());
	}

	//	@Test
	//	public void test$varargsSupport() {
	//		final Tag transform = transform(toStringMethod, new Object[] { new Object[] { newAttribute(rs) } });
	//		assertEquals(rs, ((Element) transform).getAttributes().get(0).getName());
	//	}

}
