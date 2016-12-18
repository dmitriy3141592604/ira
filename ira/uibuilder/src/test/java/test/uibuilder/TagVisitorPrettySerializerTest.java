package test.uibuilder;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagVisitorPrettySerializerTest extends TagVisitorOneLineSerializeTestBase {

	@Test
	public void test$indentSerialization() {
		final TagVisitorOneLineSerialize v = new TagVisitorPrettySerializer();

		final String expected = new IndentResultConstructorHelper() {
			{
				$("<html lang=\"ru\">");
				v.onBeforeElement();
				v.onStartElement("html");
				v.onElementAttributes(singletonList(lang("ru")));
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
