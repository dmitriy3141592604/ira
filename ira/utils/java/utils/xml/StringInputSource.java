package utils.xml;

import java.io.StringReader;

import org.xml.sax.InputSource;

public class StringInputSource extends InputSource {

	public StringInputSource(String content) {
		super(new StringReader(content));
	}

}
