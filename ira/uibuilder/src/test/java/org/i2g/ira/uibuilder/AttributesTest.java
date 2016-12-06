package org.i2g.ira.uibuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AttributesTest extends AttributeTestBase {

	@Test
	public void test$src() {
		assertEquals("src|" + randomValue, with(attributes::src));
	}

	@Test
	public void test$type() {
		assertEquals("type|" + randomValue, with(attributes::type));
	}

	@Test
	public void test$charset() {
		assertEquals("charset|" + randomValue, with(attributes::charset));
	}

	@Test
	public void test$rel() {
		assertEquals("rel|" + randomValue, with(attributes::rel));
	}

	@Test
	public void test$href() {
		assertEquals("href|" + randomValue, with(attributes::href));
	}

	@Test
	public void test$klass() {
		assertEquals("class|" + randomValue, with(attributes::klass));
	}

}
