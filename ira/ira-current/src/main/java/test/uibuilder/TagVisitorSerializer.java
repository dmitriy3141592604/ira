package test.uibuilder;

import java.util.List;

import org.i2g.ira.uibuilder.Attribute;
import org.i2g.ira.uibuilder.AttributeSerializer;
import org.i2g.ira.uibuilder.TagVisitorBase;

public class TagVisitorSerializer extends TagVisitorBase {

	private final StringBuilder sb;

	private final AttributeSerializer as = new AttributeSerializer();

	public TagVisitorSerializer(StringBuilder sb) {
		this.sb = sb;
	}

	public TagVisitorSerializer() {
		this(new StringBuilder());
	}

	@Override
	public void beforeElement() {
		sb.append("<");
	}

	@Override
	public void onStartElement(String name) {
		sb.append(name);
	}

	@Override
	public void onElementAttributes(List<Attribute> attributes) {
		as.serialize(attributes, sb);
	}

	@Override
	public void onAfterStartElement() {
		sb.append(">");
	}

	@Override
	public void beforeEndElement() {
		sb.append("</");
	}

	@Override
	public void onEndElement(String name) {
		sb.append(name);
	}

	@Override
	public void afterendElement() {
		sb.append(">");
	}

	@Override
	public void onText(String text) {
		// TODO Нужно заэскейпить
		sb.append(text);
	}

	public String resultLog() {
		return sb.toString();
	}

}
