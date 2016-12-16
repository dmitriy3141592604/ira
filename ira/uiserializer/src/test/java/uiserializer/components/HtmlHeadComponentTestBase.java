package uiserializer.components;

import static utils.collections.Collector.newCollector;
import static utils.xml.XPathUtils.evalXPath;

import java.util.Collection;
import java.util.TreeSet;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Before;

import uiserializer.UIBuilder;
import uiserializer.UIBuilderBuilder;
import utils.collections.Collector;

public abstract class HtmlHeadComponentTestBase extends ComponentTestBase {

	protected HtmlHeadComponent component;

	protected Collection<String> expected;

	protected Collector<String> expectedStyle;

	protected Collector<String> expectedMeta;

	protected Collector<String> expectedScript;

	@Before
	public void setUpHtmlHeadTestBase() {
		component = new HtmlHeadComponent();
		expected = new TreeSet<String>();
		expectedStyle = newCollector();
		expectedMeta = newCollector();
		expectedScript = newCollector();
	}

	protected String importedStylesCount(String ex) {
		final String expression = expectedStyle.remember("/html/head/link[@rel='stylesheet' and @href='" + ex + "']");
		return evalXPath(newSeralizedContent(), count(expression));
	}

	protected String importedScriptsCount(String ex) {
		final String expression = expectedScript.remember("/html/head/script[@type='text/javascript' and @src='" + ex + "']");
		return evalXPath(newSeralizedContent(), count(expression));
	}

	protected String importedMetaCount(String name, String value) {
		final String expression = expectedScript.remember("/html/head/meta[@" + name + "='" + value + "']");
		return evalXPath(newSeralizedContent(), count(expression));
	}

	private String newSeralizedContent() {
		final StringBuilder serializationTarget = new StringBuilder();
		final Element element = new Element("html");

		final UIBuilderBuilder uiBuilderBuilder = new UIBuilderBuilder();
		uiBuilderBuilder.setInterface(HTMLElements.class);
		uiBuilderBuilder.setElement(element);

		final UIBuilder builder = uiBuilderBuilder.build();
		component.render(builder.getHtml());
		builder.serializeContent(serializationTarget);
		return serializationTarget.toString();
	}

	protected String addStyle(String styleName) {
		return component.addStyle(styleName);
	}

	protected String addScript(String scriptName) {
		return component.addScript(scriptName);
	}

	private String count(String expression) {
		return "count(" + expression + ")";
	}
}
