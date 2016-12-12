package uiserializer;

import org.i2g.ira.uibuilder.HTMLElements;

import application.NamedField;
import uiserializer.components.Cmp;

public class Form extends ComponentBuilder {

	@Override
	public void render(HTMLElements html) {
		final HTMLElements form = html.form().table();
		items.forEach(item -> item.render(form));
	}

	public void add(NamedField calculatedSumm) {
		items.remember(newBinaryRow(calculatedSumm.label()));
	}

	private Cmp newBinaryRow(String label) {
		return new BinaryRow(label) {

		};
	}

}
