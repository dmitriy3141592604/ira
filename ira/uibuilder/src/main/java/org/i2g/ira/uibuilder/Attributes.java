package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeBuilder.asAttribute;

import utils.Responsibility;

@Responsibility("Отвечает за создание html аттрибутов")
public interface Attributes {

	default Attribute src(String url) {
		return asAttribute(url);
	}

	default Attribute type(String type) {
		return asAttribute(type);
	}
}