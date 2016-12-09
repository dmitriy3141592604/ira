package uiserializer;

import java.util.List;
import java.util.TreeSet;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;
import org.i2g.ira.uibuilder.UIBuilderFactory;

import test.uibuilder.DefaultMethodTransformer;
import test.uibuilder.TagVisitorSerializer;

public class UIBuilderFactoryBuilder {

	private StringBuilder sb;

	protected Element documentRoot;

	private HTMLElements html;

	protected TagVisitorSerializer visitor;

	// TODO Переместить в тест
	private TreeSet<String> tagNames;

	// TODO Переместить в тест
	private TreeSet<String> attributeValues;

	public final UIBuilderFactoryBuilder build() {

		this.tagNames = new TreeSet<String>();

		this.attributeValues = new TreeSet<String>();

		this.sb = new StringBuilder();
		documentRoot = new Element("html");
		final UIBuilderFactory factory = new UIBuilderFactory(documentRoot, new DefaultMethodTransformer());
		this.html = factory.create(HTMLElements.class);
		visitor = new IndentTagVisitorSerializer(sb) {

			@Override
			public void onStartElement(String name) {
				super.onStartElement(name);
				getTagNames().add(name);
			}

			@Override
			public void onElementAttributes(List<Attribute> attributes) {
				super.onElementAttributes(attributes);
				attributes.forEach(a -> {
					getAttributeValues().add(a.getValue());
				});
			}

		};
		return this;
	}

	public HTMLElements getHtml() {
		return html;
	}

	public String getSerializedContent() {
		documentRoot.visit(visitor);
		return getSb().toString();
	}

	public StringBuilder getSb() {
		return sb;
	}

	public TreeSet<String> getTagNames() {
		return tagNames;
	}

	public TreeSet<String> getAttributeValues() {
		return attributeValues;
	}

}