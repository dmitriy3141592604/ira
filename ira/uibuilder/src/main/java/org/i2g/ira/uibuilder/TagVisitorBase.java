package org.i2g.ira.uibuilder;

import utils.Responsibility;

@Responsibility("Дает возможность призводным классам перегружать только интересующие их методы")
public class TagVisitorBase implements TagVisitor {

	@Override
	public void beforeElement() {
	}

	@Override
	public void onStartElement(String name) {
	}

	@Override
	public void onElementAttributes(Iterable<Attribute> attributes) {
	}

	@Override
	public void onAfterStartElement() {
	}

	@Override
	public void beforeEndElement() {
	}

	@Override
	public void onEndElement(String name) {
	}

	@Override
	public void afterendElement() {
	}

	@Override
	public void onText(String text) {
	}

}
