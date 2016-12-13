package uiserializer;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Component;

public class BinaryRow implements Component, Attributes {

	private final String label;

	private final String id;

	private final String type;

	public BinaryRow(String label, String id, String type) {
		this.label = label;
		this.id = id;
		this.type = type;
	}

	@Override
	public void render(HTMLElements html) {
		final HTMLElements tr = html.tr();
		tr.td().text(label);
		tr.td().input(id(id), type(type));
	}

}
