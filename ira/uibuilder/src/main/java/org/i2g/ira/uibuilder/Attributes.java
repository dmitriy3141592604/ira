package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeBuilder.asAttribute;
import static org.i2g.ira.uibuilder.AttributeBuilder.asNamedAttribute;

import utils.Responsibility;

@Responsibility("Отвечает за создание html аттрибутов")
public interface Attributes {

	default Attribute src(String url) {
		return asAttribute(url);
	}

	default Attribute type(String type) {
		return asAttribute(type);
	}

	default Attribute charset(String charset) {
		return asAttribute(charset);
	}

	default Attribute rel(String rel) {
		return asAttribute(rel);
	}

	default Attribute href(String href) {
		return asAttribute(href);
	}

	default Attribute klass(String className) {
		return asNamedAttribute("class", className);
	}

	default Attribute id(String id) {
		return asAttribute(id);
	}
}