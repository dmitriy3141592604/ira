package uiserializer;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.HTMLElements;

public class UIBuilder {

	private final Element root;

	private final HTMLElements html;

	public UIBuilder(Element root, HTMLElements htmlSource) {
		this.root = root;
		this.html = htmlSource;
	}

	public Element getRoot() {
		return root;
	}

	public HTMLElements getHtml() {
		return html;
	}

	public void serializeContent(StringBuilder serializationTarget) {
		root.visit(new IndentTagVisitorSerializer(serializationTarget));
	}
}