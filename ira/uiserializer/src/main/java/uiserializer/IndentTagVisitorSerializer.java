package uiserializer;

import test.uibuilder.TagVisitorOneLineSerialize;

public class IndentTagVisitorSerializer extends TagVisitorOneLineSerialize {

	IndentTagVisitorSerializer(StringBuilder sb) {
		super(sb);
	}

	@Override
	public void beforeElement() {
		sb.append("\n");
		super.beforeElement();
	}
}