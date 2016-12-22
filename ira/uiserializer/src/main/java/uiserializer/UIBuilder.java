package uiserializer;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

import test.uibuilder.TagVisitorOneLineSerialize;

public class UIBuilder {

	private final Element root;

	private final HTMLElements html;

	public UIBuilder(Element root, HTMLElements htmlSource) {
		if (root == null) {
			throw new IllegalArgumentException("root argument can't be null");
		}
		if (htmlSource == null) {
			throw new IllegalArgumentException("htmlSource can't be null");
		}
		this.root = root;
		this.html = htmlSource;
	}

	public HTMLElements getHtml() {
		return html;
	}

	public StringBuilder serializeContent(StringBuilder serializationTarget) {
		root.visit(new TagVisitorOneLineSerialize(serializationTarget));
		return serializationTarget;
	}
}