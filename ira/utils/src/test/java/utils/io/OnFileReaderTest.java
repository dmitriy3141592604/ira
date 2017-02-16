package utils.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Value.newValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import utils.Value;

public class OnFileReaderTest extends OnFileReaderTestBase {

	@Test
	public void testFileReading() throws Exception {

		final File tmpFile = super.newFile();
		newFile();

		try (FileWriter fileWriter = new FileWriter(tmpFile)) {
			fileWriter.write(ethalon);
		}
		final Value<String> value = newValue();

		final OnFileReader fileReader = new OnFileReader(tmpFile);
		fileReader.accept(t -> value.setValue(t.readLine()));
		assertEquals(ethalon, value.getValue());

	}

	// TODO (tdv): Придумать как бросать исключение при передаче null
	@Ignore
	@Test
	public void testConstructorWithNullArgument() {
		exception.expect(IllegalArgumentException.class);
		new OnFileReader((File) null);
	}

	@Test
	public void testConstructorReturnValueIsNotNull() {
		createExchangePoint();
		final OnFileReader fileReader = new OnFileReader(exchangePoint);
		assertNotNull(fileReader);
	}

	@Ignore
	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		execute(x -> {
			throw new IOException("Совершенно произвольный текст");
		});
	}

	@Test
	@Ignore
	public void testFileReadingWithStringBuilder() {
		final OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile.txt"));
		final StringBuilder stringBuilder = new StringBuilder();
		reader.accept(t -> {
			stringBuilder.append(t.readLine());
		});
		assertEquals("1", stringBuilder.toString());
	}

}
