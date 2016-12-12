package uiserializer.components;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uiserializer.UIBuilderFactoryBuilder;

public class FormCmpTest {

	@Test
	public void test$sample() {
		final OldForm form = new OldForm();

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
