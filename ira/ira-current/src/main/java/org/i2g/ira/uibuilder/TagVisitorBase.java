package org.i2g.ira.uibuilder;

import java.util.List;

public class TagVisitorBase implements TagVisitor {

	@Override
	public void beforeElement() {
	}

	@Override
	public void onStartElement(String name) {
	}

	@Override
	public void onElementAttributes(List<Attribute> attributes) {
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
