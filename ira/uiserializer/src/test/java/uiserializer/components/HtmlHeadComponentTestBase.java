package uiserializer.components;

import java.util.Collection;
import java.util.TreeSet;

import org.junit.Before;

public abstract class HtmlHeadComponentTestBase extends ComponentTestBase {

	protected HtmlHeadComponent cmd;

	protected Collection<String> expected;

	@Before
	public void setUpHtmlHeadTestBase() {
		cmd = new HtmlHeadComponent(builder.getHtml());
		expected = new TreeSet<String>();
	}

	protected String expectedAttributesMessage() {
		return "expected: " + expected + " actual: " + attributeValues;
	}

	protected boolean attributesHasAll(Collection<String> expected2) {
		return attributeValues.containsAll(expected2);
	}
}
