package test.uibuilder;

public class TagVisitorPrettySerializer extends TagVisitorOneLineSerialize {

	private int indent = 0;

	private String prefix() {
		final StringBuilder retVal = new StringBuilder();
		for (int i = 0; i < indent; ++i) {
			retVal.append(" ");
		}
		return retVal.toString();
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