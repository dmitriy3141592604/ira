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

	public static class Form implements Cmp {

		// TODO Перенести в базовый класс
		private static final Attributes as = Attributes.as;

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

		private BinaryRow newBinaryRow(HTMLElements html) {
			return new BinaryRow(html);
		}

		// TODO Перенести в базовый класс
		private final Collector<Cmp> items = Collector.newCollector(new ArrayList<Cmp>());

		public void with(Consumer<Form> form) {
			form.accept(this);
		}

		public void addTextInput(Consumer<TextInput> textInput) {
			textInput.accept(items.remember(new TextInput() {

				@Override
				public void render(HTMLElements html) {
					final BinaryRow br = newBinaryRow(html);
					br.left().text(label);
					br.right().input(as.type("text"));
				}

			}));
		}

		public void addCheckBox(Consumer<CheckBox> checkBox) {
			checkBox.accept(items.remember(new CheckBox() {

				@Override
				public void render(HTMLElements html) {
					final BinaryRow br = newBinaryRow(html);
					br.left().text(label);
					br.right().input(as.type("checkbox"), as.value("" + initValue));
				}

			}));
		}

		// TODO Перенести в базовый класс
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
