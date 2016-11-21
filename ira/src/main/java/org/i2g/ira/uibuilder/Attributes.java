package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.AttributeBuilder.asAttribute;

public interface Attributes {

	default Attribute src(String url) {
		return asAttribute(url);
	}
}