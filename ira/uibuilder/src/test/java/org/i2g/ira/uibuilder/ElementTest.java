package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeHelper.newAttribute;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import testutils.RandomizedTest;

public class ElementTest implements RandomizedTest {

	@Test
	public void test$name() {
		final String name = randomString();
		assertEquals(name, new Element(name).getName());
	}

	@Test
	public void test$attriubte() {
		final String aName = randomString();
		final Element element = new Element(randomString()).addAttribute(newAttribute(aName));
		assertEquals(aName, element.getAttributes().get(0).getName());
	}

}
