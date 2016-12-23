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

		/**
		 * TODO REVIEW(fdv): super избыточно. Если в текущем классе переопределить newFile() (Для логгирования или дебага, то пользователя ждет
		 * сюрприз
		 **/
		final File tmpFile = super.newFile();

		try (FileWriter fileWriter = new FileWriter(tmpFile)) {
			fileWriter.write(ethalon);
		}
		final Value<String> value = newValue();

		final OnFileReader fileReader = new OnFileReader(tmpFile);
		/** TODO REVIEW(fdv): Скобки вокруг t избыточны **/
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
		/**
		 * TODO REVIEW(fdv): запутано. Если посмотреть комменты к createExchangePoint - то это место, где дизайн выходит боком. Объяснить сложно,
		 * логика теста запутана. Нужно обсудить словами.
		 **/
		createExchangePoint();
		final OnFileReader fileReader = new OnFileReader(exchangePoint);
		assertNotNull(fileReader);
	}

	@Test
	public void testConstructorWithMissingFile() {
		exception.expectCause(isA(FileNotFoundException.class));
		/**
		 * TODO REVIEW(fdv): В любой *nix системе есть корень с именем / При работе в linux исключение FileNotFound будет звучать очень странно.
		 * Корневой каталог всегда существует. (По крайней мене, если запустилась java и maven
		 **/
		new OnFileReader(new File("/"));
	}

	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		execute(x -> {
			/** TODO REVIEW(fdv): RuntimveException будет только тогда, когда в сообщении будет 'Some exception'? **/
			throw new IOException("Some exception");
		});
	}

	@AfterClass
	public static void testTmpFilesRemoval() {
		Boolean exists = false;
		/** TODO REVIEW(fdv): Если закомментировать весь цикл (три следующих строки) то тест будет зеленым ) **/
		for (final String s : tmpFiles) {
			final Path path = Paths.get(s);
			exists |= Files.exists(path);
		}
		assertFalse(exists);
	}

	// TODO tdv: протестировать вызов метода close

}