package utils.xml;
import java.io.StringReader;

import org.xml.sax.InputSource;

public class StringReaderInputSource extends InputSource {

	public StringReaderInputSource(String content) {
		super(new StringReader(content));
	}

}
