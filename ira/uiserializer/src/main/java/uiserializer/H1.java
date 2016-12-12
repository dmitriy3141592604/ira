package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

public class H1 extends ComponentBuilder {

	private String name;

	public void add(String name) {
		this.name = name;
	}

	@Override
	public void render(HTMLElements html) {
		html.h1().text(name);
	}

}
