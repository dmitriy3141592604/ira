package uiserializer;

import static org.i2g.ira.uibuilder.UIBuilderFactory.newUIBuilderFactory;
import static utils.SideEffect.withEffect;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;

public class UIBuilderBuilder implements SelfAware<UIBuilderBuilder> {

	private DefaultMethodTransformer valueTransformer = new DefaultMethodTransformer();

	private Class<HTMLElements> interfaceClass;

	private Element element;

	public UIBuilder build() {
		final UIBuilderFactory uiBuilderFactory = newUIBuilderFactory(element, valueTransformer);
		final HTMLElements create = uiBuilderFactory.create(interfaceClass);
		return new UIBuilder(element, create);
	}

	public UIBuilderBuilder setElement(final Element element) {
		return withEffect(this, () -> self().element = element);
	}

	public UIBuilderBuilder setInterface(Class<HTMLElements> interfaceClass) {
		return withEffect(this, () -> self().interfaceClass = interfaceClass);
	}

	public UIBuilderBuilder setValueTransformer(DefaultMethodTransformer valueTransformer) {
		return withEffect(this, () -> self().valueTransformer = valueTransformer);
	}

}