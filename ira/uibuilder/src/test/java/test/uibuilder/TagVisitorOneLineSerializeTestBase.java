package test.uibuilder;

import org.i2g.ira.uibuilder.Attributes;
import org.junit.Before;

import testutils.RandomizedTest;

public abstract class TagVisitorOneLineSerializeTestBase implements RandomizedTest, Attributes {

	protected TagVisitorOneLineSerialize visitor;

	protected String rs;

	@Before
	public final void setUpTagVisitorOneLineSerializeTestBase() {
		visitor = new TagVisitorOneLineSerialize(new StringBuilder());
		rs = randomString();

	}

	protected String result() {
		return visitor.resultLog();
	}

}
