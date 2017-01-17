package uiserializer;

import static org.junit.Assert.assertEquals;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Ignore;
import org.junit.Test;

import application.Application;

public class ApplicationSerializerTest {

	@Test
	@Ignore // IGNORED!
	public void test() {
		final StringWriter stringWriter = new StringWriter();
		try (PrintWriter out = new PrintWriter(stringWriter);) {
			new ApplicationSerializer().process(out, Application.class);
		}

		final String string = stringWriter.toString().replaceAll("\n", " ");

		assertEquals(true, string.contains("h1"));
	}

}
