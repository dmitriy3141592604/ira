package utils.io;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static utils.Value.newValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Test;

import utils.Value;

public class OnFileReaderTest extends OnFileReaderTestBase {

	@Test
	public void testFileReading() throws Exception {

		File tmpFile = super.newFile();

		try (FileWriter fileWriter = new FileWriter(tmpFile)) {
			fileWriter.write(ethalon);
		}
		final Value<String> value = newValue();

		OnFileReader fileReader = new OnFileReader(tmpFile);
		fileReader.accept((t) -> value.setValue(t.readLine()));
		assertEquals(ethalon, value.getValue());

	}

	@Test
	public void testConstructorWithNullArgument() {
		exception.expectCause(isA(NullPointerException.class));
		new OnFileReader(null);
	}

	@Test
	public void testConstructorReturnValueIsNotNull() {
		createExchangePoint();
		OnFileReader fileReader = new OnFileReader(exchangePoint);
		assertNotNull(fileReader);
	}

	@Test
	public void testConstructorWithMissingFile() {
		exception.expectCause(isA(FileNotFoundException.class));
		new OnFileReader(new File("/"));
	}

	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		execute(x -> {
			throw new IOException("Some exception");
		});
	}

	@AfterClass
	public static void testTmpFilesRemoval() {
		Boolean exists = false;
		for (String s : tmpFiles) {
			Path path = Paths.get(s);
			exists |= Files.exists(path);
		}
		assertFalse(exists);
	}
	
	// TODO tdv: протестировать вызов метода close

}