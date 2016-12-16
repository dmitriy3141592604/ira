package uiserializer;

import static org.i2g.ira.uibuilder.UIBuilderFactory.newUIBuilderFactory;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import test.uibuilder.DefaultMethodTransformer;

public class UIBuilderBuilder {

	private DefaultMethodTransformer valueTransformer = new DefaultMethodTransformer();

	private Class<HTMLElements> interfaceClass;

	private Element element;

	public UIBuilder build() {
		return new UIBuilder(element, newUIBuilderFactory(element, valueTransformer).create(interfaceClass));
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public void setInterface(Class<HTMLElements> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public void setValueTransformer(DefaultMethodTransformer valueTransformer) {
		this.valueTransformer = valueTransformer;
	}

}