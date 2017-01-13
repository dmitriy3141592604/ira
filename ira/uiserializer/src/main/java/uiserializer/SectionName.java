package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

// FIXME Протестировать
public class SectionName extends ComponentBuilderBase {

	private String name;

	public void set(String name) {
		this.name = name;
	}

	@Override
	public void render(HTMLElements html) {
		html.h1().text(name);
	}

}
