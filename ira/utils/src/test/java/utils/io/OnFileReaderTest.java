package utils.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Value.newValue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

	/**
	 * Проверка переопределения метода
	 */
	@Override
	public File newFile() {
		System.out.println("OnFileReaderTest#newFile");
		return null;
	}

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

	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		execute(x -> {
			throw new IOException("Совершенно произвольный текст");
		});
	}

	// TODO tdv: протестировать вызов метода close

}
