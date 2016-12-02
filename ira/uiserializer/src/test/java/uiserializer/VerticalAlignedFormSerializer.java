package uiserializer;

import java.util.ArrayList;
import java.util.List;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;

import utils.Responsibility;

@Responsibility("Отвечает за создание формы с вертикально расположенными элементами")
public class VerticalAlignedFormSerializer {

	private final List<HtmlInputModel> inputs = new ArrayList<>();

	private final HTMLElements elements;

	public VerticalAlignedFormSerializer(HTMLElements elements) {
		this.elements = elements;
	}

	public void add(HtmlInputModel inputModel) {
		inputs.add(inputModel);
	}

	private class SerializationHelper implements Attributes {

		public HTMLElements createTree() {
			final HTMLElements form = elements.form();
			for (final HtmlInputModel model : inputs) {
				form.label().text(model.getLabel());
				form.input(type(model.getType()));
			}
			return form;

		}

	}

	public void serialize() {
		new SerializationHelper().createTree();
	}

}
