package uiserializer;

import static utils.collections.Collector.newCollector;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import application.NamedField;
import uiserializer.components.Cmp;
import utils.collections.Collector;

public class Form extends ComponentBuilder implements Attributes {

	private String method;
	private String action;

	public static Form newForm(FormSource fs) {
		final Form form = new Form();
		form.setMethod("POST");
		form.setAction("action.do");
		return form;
	}

	private void setAction(String action) {
		this.action = action;
	}

	private void setMethod(String method) {
		this.method = method;
	}

	@Override
	public void render(HTMLElements html) {
		final HTMLElements form = html.form(formAttributes()).table();
		items.forEach(item -> item.render(form));
	}

	private Attribute[] formAttributes() {
		final Collector<Attribute> attributes = newCollector();
		attributes.remember(method(method));
		attributes.remember(action(action));
		return attributes.getStorage().toArray(new Attribute[0]);
	}

	public void add(NamedField calculatedSumm) {
		items.remember(newBinaryRow(calculatedSumm.label(), calculatedSumm.id(), "text"));
	}

	private Cmp newBinaryRow(String label, String id, String type) {
		return new BinaryRow(label, id, type);
	}

}
