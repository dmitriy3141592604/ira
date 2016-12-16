package uiserializer;

import static org.i2g.ira.uibuilder.UIBuilderFactory.newUIBuilderFactory;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;

public class UIBuilderBuilder {

	private DefaultMethodTransformer valueTransformer = new DefaultMethodTransformer();

	private Class<HTMLElements> interfaceClass;

	private Element element;

	public UIBuilder build() {
		final UIBuilderFactory uiBuilderFactory = newUIBuilderFactory(element, valueTransformer);
		final HTMLElements create = uiBuilderFactory.create(interfaceClass);
		return new UIBuilder(element, create);
	}

	public UIBuilderBuilder setElement(Element element) {
		this.element = element;
		return this;
	}

	public UIBuilderBuilder setInterface(Class<HTMLElements> interfaceClass) {
		this.interfaceClass = interfaceClass;
		return this;
	}

	public UIBuilderBuilder setValueTransformer(DefaultMethodTransformer valueTransformer) {
		this.valueTransformer = valueTransformer;
		return this;
	}

}