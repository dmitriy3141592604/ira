package test.uibuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.i2g.ira.uibuilder.Attributes;
import org.junit.Before;
import org.junit.Test;

import testutils.RandomizedTest;

public class TagVisitorOneLineSerializeTest extends TagVisitorOneLineSerializeTestBase implements RandomizedTest, Attributes {

	@Before
	public final void setUpTagVisitorOneLIneSerializeTest() {
		assertEquals("", result());
	}

	@Test
	public void test$staticMethodConstructorWithoutParameters() {
		assertEquals("", new TagVisitorOneLineSerialize().resultLog());
	}

	@Test
	public void test$beforeElement() {
		visitor.beforeElement();
		assertEquals("<", result());
	}

	@Test
	public void test$onStartElement() {
		final String rs = randomString();
		visitor.onStartElement(rs);
		assertEquals(rs, result());
	}

	@Test
	public void test$onElementAttributes() {
		visitor.onElementAttributes(Collections.singletonList(href("some.url")));
		assertEquals(" href=\"some.url\"", result());
	}

	@Test
	public void test$onAfterStartElement() {
		visitor.onAfterStartElement();
		assertEquals(">", result());
	}

	@Test
	public void test$onBeforeEndElement() {
		visitor.beforeEndElement();
		assertEquals("</", result());
	}

	@Test
	public void test$onEndElement() {
		final String randomString = randomString();
		visitor.onEndElement(randomString);
		assertEquals(randomString, result());
	}

	@Test
	public void test$afterEndElement() {
		visitor.afterendElement();
		assertEquals(">", result());
	}

	@Test
	public void test$onText() {
		final String randomString = randomString();
		visitor.onText(randomString);
		assertEquals(randomString, result());
	}

}
