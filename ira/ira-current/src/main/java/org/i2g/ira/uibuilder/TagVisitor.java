package org.i2g.ira.uibuilder;

import java.util.List;

public interface TagVisitor {

	void beforeElement();

	void onStartElement(String name);

	void onElementAttributes(List<Attribute> attributes);

	void onAfterStartElement();

	void beforeEndElement();

	void onEndElement(String name);

	void afterendElement();

	void onText(String text);

}
