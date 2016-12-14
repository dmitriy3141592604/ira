package uiserializer.components;

import static utils.Safer.safe;
import static utils.collections.Collector.newCollector;

import java.util.Collection;
import java.util.TreeSet;

import javax.xml.xpath.XPathFactory;

import org.junit.Before;

import uiserializer.UIBuilderFactoryBuilder;
import utils.collections.Collector;
import utils.xml.StringReaderInputSource;

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
		return getXPathResult(newSeralizedContent(), count(expression));
	}

	protected String importedScriptsCount(String ex) {
		final String expression = expectedScript.remember("/html/head/script[@type='text/javascript' and @src='" + ex + "']");
		return getXPathResult(newSeralizedContent(), count(expression));
	}

	protected String importedMetaCount(String name, String value) {
		final String expression = expectedScript.remember("/html/head/meta[@" + name + "='" + value + "']");
		return getXPathResult(newSeralizedContent(), count(expression));
	}

	private String newSeralizedContent() {
		final UIBuilderFactoryBuilder builder = new UIBuilderFactoryBuilder().build();
		component.render(builder.getHtml());
		return builder.serializeContentToInternalBuffer();
	}

	private String getXPathResult(String content, String expression) {
		return safe(() -> XPathFactory.newInstance().newXPath().evaluate(expression, new StringReaderInputSource(content)));
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
