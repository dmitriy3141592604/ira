package uiserializer.components;

import java.util.ArrayList;

import org.i2g.ira.uibuilder.HTMLElements;

import utils.collections.Collector;

public abstract class OldFormBase extends AbstractComponent {

	protected final Collector<Component> items = Collector.newCollector(new ArrayList<Component>());

	public void render(HTMLElements html) {
		final HTMLElements table = html.form().table();
		items.forEach(item -> item.render(table));
	}

}