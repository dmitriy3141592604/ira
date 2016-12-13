package org.i2g.ira.uibuilder;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс для обхода html дерева")
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
