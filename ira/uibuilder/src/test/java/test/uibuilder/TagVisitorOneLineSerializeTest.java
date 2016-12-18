package test.uibuilder;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class TagVisitorOneLineSerializeTest extends TagVisitorOneLineSerializeTestBase {

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
		visitor.onBeforeElement();
		assertEquals("<", result());
	}

	@Test
	public void test$onStartElement() {
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
		visitor.onBeforeEndElement();
		assertEquals("</", result());
	}

	@Test
	public void test$onEndElement() {
		visitor.onEndElement(rs);
		assertEquals(rs, result());
	}

	@Test
	public void test$afterEndElement() {
		visitor.onAfterendElement();
		assertEquals(">", result());
	}

	@Test
	public void test$onText() {
		visitor.onText(rs);
		assertEquals(rs, result());
	}

}
