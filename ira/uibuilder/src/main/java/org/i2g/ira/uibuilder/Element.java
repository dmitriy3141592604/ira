package org.i2g.ira.uibuilder;

import static utils.collections.Collector.newCollector;

import java.util.ArrayList;
import java.util.List;

import utils.collections.Collector;

public class Element implements Tag {

	private final String name;

	private final List<Attribute> attributes = new ArrayList<Attribute>();

	private final Collector<Tag> childred = newCollector();

	public Element(String name) {
		this.name = name;
	}

	public Element addAttribute(Attribute attribute) {
		attributes.add(attribute);
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
		visitor.beforeElement();
		visitor.onStartElement(name);
		visitor.onElementAttributes(attributes);
		visitor.onAfterStartElement();
		childred.forEach(t -> t.visit(visitor));
		visitor.beforeEndElement();
		visitor.onEndElement(name);
		visitor.afterendElement();
	}

}
