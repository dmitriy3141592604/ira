package uiserializer.components;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HtmlHeadComponentTest extends HtmlHeadComponentTestBase {

	@Before
	public final void setUpHtmlHeadComponentTest() {
	}

	@Test
	public void test$css() {
		serializeContent(false);

		expected.add("reset.css");
		expected.add("styles.css");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

	@Test
	public void test$utf8() {
		serializeContent(false);

		expected.add("utf-8");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

}
