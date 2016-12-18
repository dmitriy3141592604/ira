package org.i2g.ira.uibuilder;

import static utils.collections.Collector.newCollector;

import utils.Responsibility;
import utils.collections.Collector;

@Responsibility("Предоставляет интерфейс HTML тега")
public class Element implements Tag {

	@Responsibility("Хранит имя элемента")
	private final String name;

	@Responsibility("Хранит атрибуты элемента")
	private final Collector<Attribute> attributes = newCollector();

	@Responsibility("Хранит дочерние теги элемента")
	private final Collector<Tag> childred = newCollector();

	public Element(String name) {
		this.name = name;
	}

	public Element addAttribute(Attribute attribute) {
		attributes.remember(attribute);
		return this;
	}

	public String getName() {
		return name;
	}

	@Override
	public Tag addChield(Tag tag) {
		return childred.remember(tag);
	}

	@Override
	public void visit(TagVisitor visitor) {
		visitor.onBeforeElement();
		visitor.onStartElement(name);
		visitor.onElementAttributes(attributes);
		visitor.onAfterStartElement();
		childred.forEach(t -> t.visit(visitor));
		visitor.onBeforeEndElement();
		visitor.onEndElement(name);
		visitor.onAfterendElement();
	}

}
