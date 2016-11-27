package org.i2g.ira.uibuilder;

public class ProductVisitor implements ForestVisitor<Tag> {

	private final StringBuilder log = new StringBuilder();

	private final AttributeSerializer atttributeSerialize = new AttributeSerializer();

	@Override
	public void begin(Tag value) {
		if (value instanceof Element) {
			final Element element = (Element) value;
			element.getAttributes();
			log.append('<').append(element.getName());
			atttributeSerialize.serialize(element.getAttributes(), log);
			log.append('>');
		} else if (value instanceof TextElement) {
			log.append(((TextElement) value).getText());
		} else {
			throw new IllegalArgumentException("Unknown tag: " + value.getClass());
		}
	}

	@Override
	public void end(Tag value) {
		if (value instanceof Element) {
			log.append("</").append(((Element) value).getName()).append(">");
		} else if (value instanceof TextElement) {

		} else {
			throw new IllegalArgumentException("Unknown tag: " + value.getClass());
		}
	}

	public String resultLog() {
		return log.toString();
	}

}