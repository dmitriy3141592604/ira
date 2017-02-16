package utils.io;

import static org.junit.Assert.assertEquals;
import static utils.io.OnFileWriter.dumpToFile;
import static utils.io.OnFileWriter.onFileWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

public class OnFileWriterTest extends OnFileWriterTestBase {

	@Test
	public void test$staticConstructor() {
		createExchangePoint();

		onFileWriter(exchangePoint, pw -> pw.println(marker));

		assertEquals(marker, readFirstLineFromExchangePoint());
	}

	@Test
	public void test$dumpToFile() {
		createExchangePoint();

		dumpToFile(exchangePointAbsolutePath, marker);

		assertEquals(marker, readFirstLineFromExchangePoint());
	}

	@Test
	public void test$onFileWriter() {
		createExchangePoint();

		OnFileWriter.onFileWriter(exchangePointAbsolutePath, pw -> pw.println(marker));

		assertEquals(marker, readFirstLineFromExchangePoint());
	}

	@Test
	public void testFileWriting() {
		execute(out -> out.println(marker));

		assertEquals(marker, readFirstLineFromExchangePoint());
	}

	@Test
	public void testNotExistedFileAllowed() throws Exception {
		new OnFileWriter(tmpFolder.newFile());
	}

	@Test
	public void testWritingWithExceptionSupplier() throws IOException {
		final File outputFile = tmpFolder.newFile();
		final OnFileWriter onFileWriter = new OnFileWriter(() -> new FileWriter(outputFile));
		onFileWriter.accept(t -> t.println(marker));
		final List<String> s = Files.readAllLines(outputFile.toPath());
		assertEquals(marker, s.get(0));
	}

	/** Заметим: Нет декларации исключения **/
	@Test
	public void test$exceptionHandling() {

		exception.expect(RuntimeException.class);

		execute(out -> {
			throw new IOException(marker);
		});

	}

	@Test
	public void test$WriterConstructorTest() {

		final StringWriter stringWriter = new StringWriter();

		new OnFileWriter(() -> stringWriter).accept(out -> out.print(marker));

		assertEquals(marker, stringWriter.toString());
	}

}