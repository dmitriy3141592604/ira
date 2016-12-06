package uiserializer.components;

import java.util.function.Consumer;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

public class TabbedPaneCompnent implements Attributes {

	private final HTMLElements tabs;

	private final HTMLElements content;

	public TabbedPaneCompnent(HTMLElements html) {
		content = html.div(klass("tabbed-pane"));
		tabs = content.ul();
	}

	public void add(String tabNameId, Consumer<HTMLElements> paneRoot) {
		tabs.li(id(tabNameId + "")).text(tabNameId);
		paneRoot.accept(content.div(id(tabNameId + "-tab")));
	}

}
