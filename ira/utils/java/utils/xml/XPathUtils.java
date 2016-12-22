package utils.xml;

import static utils.Safer.safe;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

public class XPathUtils {

	public static String evalXPath(String content, String expression) {
		return safe(() -> newXPath().evaluate(expression, new StringInputSource(content)));
	}

	private static XPath newXPath() {
		return XPathFactory.newInstance().newXPath();
	}

}