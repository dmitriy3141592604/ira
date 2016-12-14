package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

// TODO Переименовать.
public class Header extends ComponentBuilderBase {

	private String name;

	public void set(String name) {
		this.name = name;
	}

	@Override
	public void render(HTMLElements html) {
		html.h1().text(name);
	}

}
