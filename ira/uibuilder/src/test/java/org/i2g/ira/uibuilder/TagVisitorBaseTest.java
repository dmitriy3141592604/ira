package org.i2g.ira.uibuilder;

import org.junit.Test;

import testutils.RandomizedTest;

public class TagVisitorBaseTest implements RandomizedTest {

	@Test
	public void test() {
		final TagVisitorBase visitor = new TagVisitorBase();
		visitor.beforeElement();
		visitor.onStartElement(randomString());
		visitor.onElementAttributes(null);
		visitor.onAfterStartElement();
		visitor.beforeEndElement();
		visitor.onEndElement(randomString());
		visitor.afterendElement();
		visitor.onText(randomString());

	}

}
