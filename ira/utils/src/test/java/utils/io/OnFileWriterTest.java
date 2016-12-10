package utils.io;

import static org.junit.Assert.assertEquals;
import static utils.Value.newValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Test;

import testutils.RandomizedTest;
import utils.Value;

public class OnFileWriterTest extends OnFileWriterTestBase implements RandomizedTest {

	@Test
	public void test$staticConstructor() {
		createExchangePoint();
		OnFileWriter.onFileWriter(exchangePoint, pw -> {
		});
	}

	@Test
	public void testFileWriting() throws Exception {

		execute(out -> {
			out.println(marker);
		});

		final Value<String> value = newValue();

		try (final BufferedReader reader = new BufferedReader(new FileReader(exchangePoint))) {
			value.setValue(reader.readLine());
		}

		assertEquals(marker, value.getValue());

	}

	/** Заметим: Нет декларации исключения **/
	@Test
	public void test$exceptionHandling() {

		exception.expect(RuntimeException.class);

		execute(out -> {
			throw new IOException("some error");
		});

	}

	@Test
	public void test$unexpectedFileNameException() {
		exception.expect(RuntimeException.class);
		new OnFileWriter(new File("/"));
	}

	@Test
	public void test$WriterConstructorTest() {
		final StringWriter stringWriter = new StringWriter();
		new OnFileWriter(stringWriter).accept(out -> out.print("hello"));
		assertEquals("hello", stringWriter.toString());
	}

	@Test
	public void test$null() {
		exception.expect(RuntimeException.class);
		new OnFileWriter((Writer) null).accept(out -> out.print("hello"));
		//assertEquals("hello", stringWriter.toString());
	}

}