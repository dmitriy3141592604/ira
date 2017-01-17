package test.uibuilder;

import java.util.ArrayList;
import java.util.List;

public class TagVisitorPrettySerializer extends TagVisitorOneLineSerialize {

	private static final int MAX_INDENT_LEVEL = 50;

	private static final List<String> prefixes = new ArrayList<String>();

	static {
		for (int level = 0; level < MAX_INDENT_LEVEL; ++level) {
			final StringBuilder indent = new StringBuilder();
			for (int i = 0; i < level; ++i) {
				indent.append(" ");
			}
			prefixes.add(indent.toString());
		}
	}

	private int indent = 0;

	public TagVisitorPrettySerializer(StringBuilder sb) {
		super(sb);
	}

	public TagVisitorPrettySerializer() {
		super();
	}

	private String prefix() {
		// TODO Тут когда-то появится NPE. Нужна стратегия, которая будет формировать отступ. Желательно с кешированием
		return prefixes.get(indent);
	}

	@Override
	public void onBeforeElement() {
		sb.append(prefix());
		super.onBeforeElement();
		indent++;
	}

	@Override
	public void onStartElement(String name) {
		super.onStartElement(name);
	}

	@Override
	public void onAfterStartElement() {
		super.onAfterStartElement();
		sb.append("\n");
	}

	@Override
	public void onBeforeEndElement() {
		indent--;
		sb.append(prefix());
		super.onBeforeEndElement();
	}

	@Override
	public void onEndElement(String name) {
		super.onEndElement(name);
	}

	@Override
	public void onAfterendElement() {
		super.onAfterendElement();
		sb.append("\n");
	}

	@Override
	public void onText(String text) {
		sb.append(prefix());
		super.onText(text);
		sb.append("\n");
	}
}