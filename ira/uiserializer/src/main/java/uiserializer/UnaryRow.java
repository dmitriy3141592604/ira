package uiserializer;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Component;

public class UnaryRow implements Component, Attributes {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements tr = html.tr();
		tr.td(colspan(2)).input(type("submit"));
	}

}
