package uiserializer.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HtmlHeadComponentTest extends HtmlHeadComponentTestBase {

	@Test
	public void test$utf8() {
		assertEquals("1", importedMetaCount("charset", "utf-8"));
	}

	@Test
	public void test$rest() {
		assertEquals("0", importedStylesCount("reset.css"));

		addStyle("reset.css");

		assertEquals("1", importedStylesCount("reset.css"));
	}

	@Test
	public void test$styles() {
		assertEquals("0", importedStylesCount("styles.css"));

		addStyle("styles.css");

		assertEquals("1", importedStylesCount("styles.css"));
	}

	@Test
	public void test$jquery() {
		assertEquals("0", importedScriptsCount("../jquery-3.1.1.js"));

		addScript("../jquery-3.1.1.js");

		assertEquals("1", importedScriptsCount("../jquery-3.1.1.js"));
	}

}
