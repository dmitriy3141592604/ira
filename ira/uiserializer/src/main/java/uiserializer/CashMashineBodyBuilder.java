package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

@Concrete
public class CashMashineBodyBuilder extends ComponentBuilder {

	@Override
	public void render(HTMLElements html) {
		logger.info("Начинаю отрисовку");
		final HTMLElements body = html.body();
		items.forEach(item -> item.render(body));
	}

}
