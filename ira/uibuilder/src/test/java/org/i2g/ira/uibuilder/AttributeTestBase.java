package org.i2g.ira.uibuilder;

import java.util.function.Function;

import org.junit.Before;

import testutils.RandomizedTest;

public class AttributeTestBase implements RandomizedTest {

	protected Attributes attributes;

	protected String randomValue;

	@Before
	public final void setUpAttributesTestBase() {
		attributes = new Attributes() {

		};
		randomValue = randomString();
	}

	protected String with(Function<String, Attribute> valueTransformer) {
		final Attribute attribute = valueTransformer.apply(randomValue);
		return attribute.getName() + "|" + attribute.getValue();
	}

}