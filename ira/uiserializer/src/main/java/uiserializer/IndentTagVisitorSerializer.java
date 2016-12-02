package uiserializer;

import test.uibuilder.TagVisitorSerializer;

public class IndentTagVisitorSerializer extends TagVisitorSerializer {

	IndentTagVisitorSerializer(StringBuilder sb) {
		super(sb);
	}

	@Override
	public void beforeElement() {
		sb.append("\n");
		super.beforeElement();
	}
}