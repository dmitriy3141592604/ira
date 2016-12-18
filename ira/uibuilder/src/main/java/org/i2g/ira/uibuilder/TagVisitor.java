package org.i2g.ira.uibuilder;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс для обхода html дерева")
public interface TagVisitor {

	void onBeforeElement();

	void onStartElement(String name);

	void onElementAttributes(Iterable<Attribute> attributes);

	void onAfterStartElement();

	void onBeforeEndElement();

	void onEndElement(String name);

	void onAfterendElement();

	void onText(String text);

}
