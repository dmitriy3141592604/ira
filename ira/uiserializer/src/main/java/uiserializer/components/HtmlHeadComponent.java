package uiserializer.components;

import static utils.collections.Collector.newCollector;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.ComponentBuilderBase;
import utils.Responsibility;
import utils.collections.Collector;

@Responsibility("Создает заголовок html страницы")
public class HtmlHeadComponent extends ComponentBuilderBase implements Attributes {

	private final Attribute stylesheetAttribute = rel("stylesheet");

	private final Attribute javaScript = type("text/javascript");

	private final Collector<String> styles = newCollector();

	private final Collector<String> scripts = newCollector();

	public String addScript(String scriptName) {
		return scripts.remember(scriptName);
	}

	public String addStyle(String styleName) {
		return styles.remember(styleName);
	}

	@Override
	public void render(HTMLElements html) {
		final HTMLElements head = html.head();

		head.meta(charset("utf-8"));

		styles.forEach(style -> {
			logger.info("Добавляю стиль: {}", style);
			head.link(stylesheetAttribute, href(style));
		});
		scripts.forEach(script -> head.script(javaScript, src(script)));
	}

}
