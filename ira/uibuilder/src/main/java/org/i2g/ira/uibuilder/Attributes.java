package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeBuilder.asAttribute;
import static org.i2g.ira.uibuilder.AttributeBuilder.asNamedAttribute;

import utils.Responsibility;

@Responsibility("Отвечает за создание html аттрибутов")
public interface Attributes {

	public static final Attributes as = new Attributes() {
	};

	default Attribute role(String value) {
		return asAttribute(value);
	}

	default Attribute action(String value) {
		return asAttribute(value);
	}

	default Attribute charset(String charset) {
		return asAttribute(charset);
	}

	default Attribute colspan(int columnsCount) {
		return asAttribute(columnsCount);
	}

	default Attribute href(String href) {
		return asAttribute(href);
	}

	default Attribute id(String id) {
		return asAttribute(id);
	}

	default Attribute klass(String className) {
		return asNamedAttribute("class", className);
	}

	default Attribute lang(String lang) {
		return asAttribute(lang);
	}

	default Attribute method(String value) {
		return asAttribute(value);
	}

	default Attribute rel(String rel) {
		return asAttribute(rel);
	}

	default Attribute src(String url) {
		return asAttribute(url);
	}

	default Attribute type(String type) {
		return asAttribute(type);
	}

	default Attribute value(String value) {
		return asAttribute(value);
	}
}