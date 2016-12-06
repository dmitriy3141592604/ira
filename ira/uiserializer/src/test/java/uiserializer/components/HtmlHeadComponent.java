package uiserializer.components;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

public class HtmlHeadComponent implements Attributes {

	public HtmlHeadComponent(HTMLElements html) {
		final HTMLElements head = html.head();
		head.meta(charset("utf-8"));
		head.link(rel("stylesheet"), href("reset.css"));
		head.link(rel("stylesheet"), href("styles.css"));
		head.script(type("text/javascript"), src("../jquery-3.1.1.js"));
		head.script(type("text/javascript"), src("../scripts.js"));
		// TODO Хак, нужно убрать из реализации HeadComponent
		head.script(type("text/javascript"), src("../tabbedPane.js"));
	}

}
