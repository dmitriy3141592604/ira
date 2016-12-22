package uiserializer.components;

import static utils.xml.XPathUtils.evalXPath;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Before;

import uiserializer.UIBuilder;
import uiserializer.UIBuilderBuilder;

public abstract class ComponentTestBase<ComponentType extends Component> {

	protected ComponentType component;

	protected abstract ComponentType newComponent();

	@Before
	public final void setUpComponentTestBase() {
		component = newComponent();
	}

	private String serializedContent() {
		final UIBuilderBuilder uiBuilderBuilder = new UIBuilderBuilder();

		uiBuilderBuilder.setInterface(HTMLElements.class);
		uiBuilderBuilder.setElement(new Element("html"));

		final UIBuilder builder = uiBuilderBuilder.build();

		component.render(builder.getHtml());

		return new String(builder.serializeContent(new StringBuilder()));
	}

	protected int occurenceCount(String baseExpression) {
		final String expressionOccurenceCounter = "count(" + baseExpression + ")";
		return Integer.valueOf(evalXPath(serializedContent(), expressionOccurenceCounter));
	}

}