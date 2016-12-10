package org.i2g.ira.uibuilder;

public interface TagVisitor {

	void beforeElement();

	void onStartElement(String name);

	void onElementAttributes(Iterable<Attribute> attributes);

	void onAfterStartElement();

	void beforeEndElement();

	void onEndElement(String name);

	void afterendElement();

	void onText(String text);

}
