package uiserializer.components;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.function.Consumer;

import org.i2g.ira.uibuilder.Attributes;
import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Test;

import uiserializer.UIBuilderFactoryBuilder;
import utils.collections.Collector;

public class FormCmpTest {

	public interface Cmp {

		void render(HTMLElements html);

	}

	public abstract static class TextInput implements Cmp {

		protected String label;

		protected String initValue;

		void label(String label) {
			this.label = label;
		}

		void initValue(String initValue) {
			this.initValue = initValue;
		}

	}

	public abstract static class CheckBox implements Cmp {

		protected String label;

		protected Boolean initValue;

		void label(String label) {
			this.label = label;
		}

		void initValue(Boolean initValue) {
			this.initValue = initValue;
		}
	}

	public static class Form implements Cmp {

		private static final Attributes as = Attributes.as;

		private final Collector<Cmp> items = Collector.newCollector(new ArrayList<Cmp>());

		public void with(Consumer<Form> form) {
			form.accept(this);
		}

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

		public void addTextInput(Consumer<TextInput> textInput) {
			textInput.accept(register(new TextInput() {

				@Override
				public void render(HTMLElements html) {
					final BinaryRow br = newBinaryRow(html);
					br.left().text(label);
					br.right().input(as.type("text"));
				}

			}));
		}

		private BinaryRow newBinaryRow(HTMLElements html) {
			return new BinaryRow(html);
		}

		private <T extends Cmp> T register(T row) {
			return items.remember(row);
		}

		public void addCheckBox(Consumer<CheckBox> checkBox) {

			checkBox.accept(register(new CheckBox() {

				@Override
				public void render(HTMLElements html) {
					final BinaryRow br = newBinaryRow(html);
					br.left().text(label);
					br.right().input(as.type("checkbox"), as.value("" + initValue));
				}

			}));
		}

		@Override
		public void render(HTMLElements html) {
			final HTMLElements table = html.form().table();
			items.forEach(item -> item.render(table));
		}

	}

	@Test
	public void test$sample() {
		final Form form = new Form();

		form.with($ -> {
			$.addTextInput(t -> {
				t.label("text input label");
				t.initValue("my init value");

			});
			$.addCheckBox(ch -> {
				ch.label("check box");
				ch.initValue(true);
			});
		});

		final UIBuilderFactoryBuilder b = new UIBuilderFactoryBuilder().build();

		form.render(b.getHtml());

		assertNotNull("", b.getSerializedContent());

	}

}
