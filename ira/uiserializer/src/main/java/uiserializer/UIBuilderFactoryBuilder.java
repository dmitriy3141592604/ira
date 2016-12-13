package uiserializer;

import java.util.Set;
import java.util.TreeSet;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;
import test.uibuilder.TagVisitorOneLineSerialize;

public class UIBuilderFactoryBuilder {

	private StringBuilder sb;

	protected Element root;

	private HTMLElements html;

	protected TagVisitorOneLineSerialize visitor;

	// TODO Переместить в тест
	private Set<String> tagNames;

	// TODO Переместить в тест
	private Set<String> attributeValues;

	public final UIBuilderFactoryBuilder build() {
		final Class<HTMLElements> interfaceClass = HTMLElements.class;
		this.tagNames = new TreeSet<String>();
		this.attributeValues = new TreeSet<String>();
		this.sb = new StringBuilder();
		this.root = new Element("html");

		final DefaultMethodTransformer valueTransformer = new DefaultMethodTransformer();

		final UIBuilderFactory factory = new UIBuilderFactory(root, valueTransformer);
		this.html = factory.create(interfaceClass);

		visitor = new IndentTagVisitorSerializer(sb) {

			@Override
			public void onStartElement(String name) {
				super.onStartElement(name);
				getTagNames().add(name);
			}

			@Override
			public void onElementAttributes(Iterable<Attribute> attributes) {
				super.onElementAttributes(attributes);
				attributes.forEach(a -> getAttributeValues().add(a.getValue()));
			}

		};
		return this;
	}

	public HTMLElements getHtml() {
		return html;
	}

	public String getSerializedContent() {
		root.visit(visitor);
		return sb.toString();
	}

	public StringBuilder getSb() {
		return sb;
	}

	public Set<String> getTagNames() {
		return tagNames;
	}

	public Set<String> getAttributeValues() {
		return attributeValues;
	}

}