package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeHelper.newAttribute;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultMethodTransformerTest extends DefaultMethodTransformerTestBase {

	@Test
	public void test$stringArgument() {
		assertEquals(rs, asTextElement(transform(toStringMethod, rs)).getText());
	}

	@Test
	public void test$unsupportedArgumet() {
		exception.expect(IllegalArgumentException.class);

		transform(toStringMethod, runnable);
	}

	@Test
	public void test$argsIsNull() {
		assertEquals("toString", asElement(transform(toStringMethod)).getName());
	}

	@Test
	public void test$twoArgument() {
		assertEquals("toString", asElement(transform(toStringMethod, newAttribute("one"), newAttribute("two"))).getName());
	}

	// @Test
	// public void test$varargsSupport() {
	// final Tag transform = transform(toStringMethod, new Object[] { new
	// Object[] { newAttribute(rs) } });
	// assertEquals(rs, ((Element) transform).getAttributes().get(0).getName());
	// }

}
