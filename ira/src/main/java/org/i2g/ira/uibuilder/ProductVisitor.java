package org.i2g.ira.uibuilder;

public class ProductVisitor implements ForestVisitor<String> {

	private final StringBuilder log = new StringBuilder();

	@Override
	public void begin(String value) {
		log.append('<').append(value).append('>');
	}

	@Override
	public void endValue(String value) {
		log.append("</").append(value).append(">");
	}

	public String resultLog() {
		return log.toString();
	}

}