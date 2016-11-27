package org.i2g.ira.uibuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import testutils.RandomizedTest;
import utils.IraTest;

public class TextElementTest extends IraTest implements RandomizedTest {

	private StringBuilder sb;

	@Before
	public void setUpTextElementTestBase() {
		sb = new StringBuilder();
	}

	@Test
	public void test$start() {
		new TextElement(randomString()).start(sb);
		assertEquals("", sb.toString());

	}

	@Test
	public void test$end() {
		new TextElement(randomString()).end(sb);
		assertEquals("", sb.toString());

	}

	@Test
	public void test$body() {
		final String body = randomString();
		new TextElement(body).body(sb);
		assertEquals(body, sb.toString());

	}

}
