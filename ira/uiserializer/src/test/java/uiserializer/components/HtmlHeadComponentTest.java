package uiserializer.components;

import static org.junit.Assert.assertEquals;
import static testutils.MutableAssert.assertMutation;

import org.junit.Test;

public class HtmlHeadComponentTest extends HtmlHeadComponentTestBase {

	@Test
	public void test$utf8() {
		assertEquals(1, importedMetaCount("charset", "utf-8"));
	}

	@Test
	public void test$rest() {
		assertMutation(0, 1, () -> importedStylesCount("reset.css"), () -> addStyle("reset.css"));
	}

	@Test
	public void test$styles() {
		assertMutation(0, 1, () -> importedStylesCount("styles.css"), () -> addStyle("styles.css"));
	}

	@Test
	public void test$jquery() {
		assertMutation(0, 1, () -> importedScriptsCount("../jquery-3.1.1.js"), () -> addScript("../jquery-3.1.1.js"));
	}

}
