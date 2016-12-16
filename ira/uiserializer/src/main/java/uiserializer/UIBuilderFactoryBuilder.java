package uiserializer;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.TagVisitor;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;

public class UIBuilderFactoryBuilder {

	private StringBuilder sb;

	private Element root;

	private HTMLElements html;

	private TagVisitor visitor;

	public final UIBuilderFactoryBuilder build() {
		this.sb = new StringBuilder();

		final Element element = new Element("html");
		this.root = element;
		final DefaultMethodTransformer valueTransformer = new DefaultMethodTransformer();
		final UIBuilderFactory factory = new UIBuilderFactory(element, valueTransformer);

		final Class<HTMLElements> interfaceClass = HTMLElements.class;
		this.html = factory.create(interfaceClass);

		visitor = new IndentTagVisitorSerializer(sb);
		return this;

	}

	public HTMLElements getHtml() {
		return html;
	}

	public String serializeContentToInternalBuffer() {
		root.visit(visitor);
		return sb.toString();
	}

	public StringBuilder getSb() {
		return sb;
	}

}