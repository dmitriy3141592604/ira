package uiserializer.components;

import static utils.collections.Collector.newCollector;

import org.junit.Before;

import utils.collections.Collector;

public abstract class HtmlHeadComponentTestBase extends ComponentTestBase<HtmlHeadComponent> {

	protected Collector<String> expectedStyle;

	protected Collector<String> expectedMeta;

	protected Collector<String> expectedScript;

	@Before
	public void setUpHtmlHeadTestBase() {
		newComponent();
		expectedStyle = newCollector();
		expectedMeta = newCollector();
		expectedScript = newCollector();
	}

	@Override
	protected final HtmlHeadComponent newComponent() {
		return component = new HtmlHeadComponent();
	}

	protected int importedStylesCount(String ex) {
		return occurenceCount(expectedStyle.remember("/html/head/link[@rel='stylesheet' and @href='" + ex + "']"));
	}

	protected int importedScriptsCount(String ex) {
		return occurenceCount(expectedScript.remember("/html/head/script[@type='text/javascript' and @src='" + ex + "']"));
	}

	protected int importedMetaCount(String name, String value) {
		return occurenceCount(expectedMeta.remember("/html/head/meta[@" + name + "='" + value + "']"));
	}

	protected String addStyle(String styleName) {
		return component.addStyle(styleName);
	}

	protected String addScript(String scriptName) {
		return component.addScript(scriptName);
	}
}
