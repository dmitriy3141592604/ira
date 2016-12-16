package uiserializer.components;

import static org.junit.Assert.assertNotNull;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Test;

import uiserializer.UIBuilder;
import uiserializer.UIBuilderBuilder;

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

		final StringBuilder serializationTarget = new StringBuilder();
		final Element element = new Element("html");
		final UIBuilderBuilder r = new UIBuilderBuilder();
		r.setInterface(HTMLElements.class);
		r.setElement(element);
		final UIBuilder b = r.build();

		form.render(b.getHtml());
		b.serializeContent(serializationTarget);

		final String serializeContentToInternalBuffer = serializationTarget.toString();
		assertNotNull("", serializeContentToInternalBuffer);

	}

}
