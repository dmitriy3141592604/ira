package utils.io;

import static org.junit.Assert.assertEquals;
import static utils.Value.newValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;

import org.junit.Ignore;
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

	@Test
	public void testNotExistedFileAllowed() throws IOException {
		OnFileWriter writer = new OnFileWriter(tmpFolder.newFile());
	}

	@Test
	public void testWritingWithExceptionSupplier() throws IOException {
		File outputFile = tmpFolder.newFile();
		OnFileWriter onFileWriter = new OnFileWriter(() -> new FileWriter(outputFile));
		onFileWriter.accept(t -> t.println(marker));
		List<String> s = Files.readAllLines(outputFile.toPath());
		assertEquals(s.get(0), marker);
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
	public void test$WriterConstructorTest() {
		final StringWriter stringWriter = new StringWriter();
		new OnFileWriter(() -> stringWriter).accept(out -> out.print(marker));
		assertEquals(marker, stringWriter.toString());
	}

	// TODO (tdv): Придумать как бросать исключение при передаче нереального
	// файла
	@Ignore
	@Test
	public void test$unexpectedFileNameException() {
		exception.expect(RuntimeException.class);
		new OnFileWriter(new File("/"));
	}

	// TODO (tdv): Придумать как бросать исключение при передаче null
	@Ignore
	@Test
	public void test$null() {
		exception.expect(RuntimeException.class);
		new OnFileWriter((File) null).accept(out -> out.print("hello"));
	}

}