package test.uibuilder;

import org.junit.Before;

public abstract class TagVisitorOneLineSerializeTestBase {

	protected TagVisitorOneLineSerialize visitor;

	protected StringBuilder sb;

	@Before
	public final void setUpTagVisitorOneLineSerializeTestBase() {
		sb = new StringBuilder();
		visitor = new TagVisitorOneLineSerialize(sb);
	}

	protected String result() {
		return sb.toString();
	}

}
