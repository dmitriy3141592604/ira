package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

public class Div extends ComponentBuilder {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements div = html.div();
		items.forEach(item -> item.render(div));
	}

}
