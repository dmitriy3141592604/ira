package org.i2g.ira.uibuilder;

import java.util.ArrayList;
import java.util.List;

public class Element implements Tag {

	private final String name;

	private final List<Attribute> attributes = new ArrayList<Attribute>();

	private final List<Tag> childred = new ArrayList<Tag>();

	private final AttributeSerializer attributeSerializer = new AttributeSerializer();

	public Element(String name) {
		this.name = name;
	}

	public Element addAttribute(Attribute attribute) {
		attributes.add(attribute);
		return this;
	}

	@Override
	public void body(StringBuilder log) {
		log.append("");
	}

	@Override
	public void start(StringBuilder log) {
		log.append("</");
		log.append(name);
		attributes.forEach(a -> attributeSerializer.serialize(a, log));
		log.append(">");
	}

	@Override
	public void end(StringBuilder log) {
		log.append("<");
		log.append(name);
		log.append(">");
	}

	public String getName() {
		return name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	@Override
	public Tag addChield(Tag tag) {
		childred.add(tag);
		return tag;
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
