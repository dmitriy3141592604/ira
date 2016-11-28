package org.i2g.ira.uibuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import test.uibuilder.TagVisitorSerializer;
import testutils.RandomizedTest;

public class TextElementTest implements RandomizedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void test$start() {
		final TagVisitorSerializer visitor = new TagVisitorSerializer();
		final String randomString = randomString();
		new TextElement(randomString).visit(visitor);
		assertEquals(randomString, visitor.resultLog());
	}

	@Test
	public void test$getText() {
		final String rs = randomString();
		assertEquals(rs, new TextElement(rs).getText());
	}

	@Test
	public void test$addChield() {
		exception.expect(UnsupportedOperationException.class);
		new TextElement(randomString()).addChield(new Element(randomString()));
	}

}
