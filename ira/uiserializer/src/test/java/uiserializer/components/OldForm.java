package uiserializer.components;

import java.util.function.Consumer;

import org.i2g.ira.uibuilder.HTMLElements;

public class OldForm extends OldFormBase implements Component {

	private final static class BinaryRow {

		private final HTMLElements left;

		private final HTMLElements right;

		public BinaryRow(HTMLElements html) {
			final HTMLElements tr = html.tr();
			left = tr.td();
			right = tr.td();
		}

		public HTMLElements left() {
			return left;
		}

		public HTMLElements right() {
			return right;
		}

	}

	private OldForm.BinaryRow newBinaryRow(HTMLElements html) {
		return new BinaryRow(html);
	}

	public void with(Consumer<OldForm> form) {
		form.accept(this);
	}

	public void addTextInput(Consumer<TextInput> textInput) {
		textInput.accept(items.remember(new TextInput() {

			@Override
			public void render(HTMLElements html) {
				final OldForm.BinaryRow br = newBinaryRow(html);
				br.left().text(label);
				br.right().input(as.type("text"));
			}

		}));
	}

	public void addCheckBox(Consumer<CheckBox> checkBox) {
		checkBox.accept(items.remember(new CheckBox() {

			@Override
			public void render(HTMLElements html) {
				final OldForm.BinaryRow br = newBinaryRow(html);
				br.left().text(label);
				br.right().input(as.type("checkbox"), as.value("" + initValue));
			}

		}));
	}

}