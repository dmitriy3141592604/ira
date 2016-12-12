package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

import uiserializer.components.Cmp;

public class BinaryRow implements Cmp {

	private final String label;

	public BinaryRow(String label) {
		this.label = label;
	}

	@Override
	public void render(HTMLElements html) {
		final HTMLElements tr = html.tr();
		tr.td().text(label);
		tr.td();
	}

}
