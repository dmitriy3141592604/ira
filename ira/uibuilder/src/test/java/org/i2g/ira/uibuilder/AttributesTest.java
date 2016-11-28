package org.i2g.ira.uibuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import testutils.RandomizedTest;

public class AttributesTest implements RandomizedTest {

	@Test
	public void test() {
		final Attributes attributes = new Attributes() {

		};
		final String randomValue = randomString();
		assertEquals(randomValue, attributes.src(randomValue).getValue());
	}

}
