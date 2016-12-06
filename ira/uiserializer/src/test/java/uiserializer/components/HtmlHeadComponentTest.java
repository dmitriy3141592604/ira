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
		serializeContent();

		expected.add("reset.css");
		expected.add("styles.css");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

	@Test
	public void test$utf8() {
		serializeContent();

		expected.add("utf-8");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

	@Test
	public void test$jquery() {
		serializeContent();

		expected.add("../jquery-3.1.1.js");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

	@Test
	public void test$customScripts() {
		serializeContent();

		expected.add("../scripts.js");

		assertEquals(expectedAttributesMessage(), true, attributesHasAll(expected));
	}

}
