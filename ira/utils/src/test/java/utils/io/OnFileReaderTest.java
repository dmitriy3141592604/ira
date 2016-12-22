package utils.io;

//import static org.hamcrest.Matcher.*;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.*;
import static utils.Value.newValue;

import java.io.BufferedReader;
import java.io.File;
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
		System.out.println(tmpFile.getAbsolutePath());
		
		try(FileWriter fileWriter = new FileWriter(tmpFile)) {
			fileWriter.write(ethalon);
		}
		final Value<String> value = newValue();

		OnFileReader fileReader = new OnFileReader(tmpFile);
		fileReader.accept( (t) -> value.setValue(t.readLine()));
		assertEquals(ethalon, value.getValue());

	}
	
	@Test
	public void testConstructorWithNullArgument() {
		exception.expectCause(isA(NullPointerException.class));
		new OnFileReader(null);
	}
	
	@Test
	public void testConstructorReturnValue() {
		createExchangePoint();
		OnFileReader fileReader = new OnFileReader(exchangePoint);
		assertNotNull(fileReader);
	}
	
	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		
		execute(new ExceptionConsumer<BufferedReader>() {

			@Override
			public void accept(BufferedReader t) throws Exception {
				throw new IOException("Some exception");
			}
		});
	}
	
	@AfterClass
	public static void testTmpFilesRemoval() {
		Boolean exists = false;
		for(String s : tmpFiles) {
			Path path = Paths.get(s);
			exists |= Files.exists(path);
		}
		assertFalse(exists);
	}
	
}