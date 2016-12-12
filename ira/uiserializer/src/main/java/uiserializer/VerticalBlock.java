package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

public class VerticalBlock extends ComponentBuilder {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements div = html.div();
		items.forEach(cmp -> cmp.render(div));
	}

}
