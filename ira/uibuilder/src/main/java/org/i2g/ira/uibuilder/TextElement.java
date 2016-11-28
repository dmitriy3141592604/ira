package org.i2g.ira.uibuilder;

import utils.NotTested;

@NotTested
public class TextElement implements Tag {

	private final String text;

	public TextElement(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public Tag addChield(Tag tag) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void visit(TagVisitor visitor) {
		visitor.onText(text);
	}

}
