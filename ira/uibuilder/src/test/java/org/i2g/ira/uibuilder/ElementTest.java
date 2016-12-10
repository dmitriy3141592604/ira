package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeHelper.newAttribute;
import static org.junit.Assert.assertEquals;
import static utils.Value.newValue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import testutils.RandomizedTest;
import utils.Value;

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

		final Value<List<Attribute>> visitedAttributes = newValue(new ArrayList<Attribute>());
		element.visit(new TagVisitorBase() {

			@Override
			public void onElementAttributes(Iterable<Attribute> attributes) {
				attributes.forEach(a -> visitedAttributes.getValue().add(a));
			}

		});
		assertEquals(aName, visitedAttributes.getValue().get(0).getName());
	}

}
