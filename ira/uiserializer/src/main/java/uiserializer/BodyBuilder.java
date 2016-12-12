package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

public class BodyBuilder extends ComponentBuilder {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements body = html.body();
		items.forEach(item -> item.render(body));
	}

}
