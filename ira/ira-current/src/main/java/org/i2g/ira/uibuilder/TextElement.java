package org.i2g.ira.uibuilder;

import utils.NotTested;

@NotTested
public class TextElement implements Tag {

	private final String text;

	public TextElement(String text) {
		this.text = text;
	}

	@Override
	public void start(StringBuilder log) {
	}

	@Override
	public void end(StringBuilder log) {

	}

	@Override
	public void body(StringBuilder log) {
		log.append(getText());
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
