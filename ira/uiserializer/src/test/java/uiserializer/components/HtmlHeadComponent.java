package uiserializer.components;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

public class HtmlHeadComponent implements Attributes {

	public HtmlHeadComponent(HTMLElements html) {
		final HTMLElements head = html.head();
		head.meta(charset("utf-8"));
		head.link(rel("stylesheet"), href("reset.css"));
		head.link(rel("stylesheet"), href("styles.css"));
	}

}
