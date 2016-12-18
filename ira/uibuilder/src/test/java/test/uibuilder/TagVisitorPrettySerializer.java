package test.uibuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class IndentResultConstructor {

	private final StringBuilder sb = new StringBuilder();

	private final String eol = "\n";

	public void $(String v) {
		n("", v);
	}

	public void $$(String v) {
		n(" ", v);
	}

	public void $$$(String v) {
		n("  ", v);
	}

	public void $$$$(String v) {
		n("   ", v);
	}

	public void $$$$$(String v) {
		n("    ", v);
	}

	private StringBuilder n(String preifx, String v) {
		return sb.append(preifx).append(v).append(eol);
	}

	public String getLog() {
		return sb.toString();
	}
}

public class TagVisitorPrettySerializer extends TagVisitorOneLineSerializeTestBase {

	@Test
	public void test$indentSerialization() {
		final TagVisitorOneLineSerialize v = new TagVisitorOneLineSerialize() {
			int indent = 0;

			String prefix() {
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

		};

		final String expected = new IndentResultConstructor() {
			{
				$("<html>");
				v.onBeforeElement();
				v.onStartElement("html");
				v.onAfterStartElement();

				$$("<head>");
				v.onBeforeElement();
				v.onStartElement("head");
				v.onAfterStartElement();

				$$("</head>");
				v.onBeforeEndElement();
				v.onEndElement("head");
				v.onAfterendElement();

				$$("<body>");
				v.onBeforeElement();
				v.onStartElement("body");
				v.onAfterStartElement();

				$$$("<div>");
				v.onBeforeElement();
				v.onStartElement("div");
				v.onAfterStartElement();

				$$$$("<h1>");
				v.onBeforeElement();
				v.onStartElement("h1");
				v.onAfterStartElement();

				$$$$$("Hello");
				v.onText("Hello");

				$$$$("</h1>");
				v.onBeforeEndElement();
				v.onEndElement("h1");
				v.onAfterendElement();

				$$$("</div>");
				v.onBeforeEndElement();
				v.onEndElement("div");
				v.onAfterendElement();

				$$("</body>");
				v.onBeforeEndElement();
				v.onEndElement("body");
				v.onAfterendElement();

				$("</html>");
				v.onBeforeEndElement();
				v.onEndElement("html");
				v.onAfterendElement();
			}

		}.getLog();

		assertEquals(expected, v.resultLog());

	}

}
