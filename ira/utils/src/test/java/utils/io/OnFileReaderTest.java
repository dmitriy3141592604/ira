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
import org.junit.Ignore;
import org.junit.Test;

import utils.Value;

public class OnFileReaderTest extends OnFileReaderTestBase {

	@Test
	public void testFileReading() throws Exception {

		/**
		 * TODO REVIEW(fdv): super избыточно. Если в текущем классе
		 * переопределить newFile() (Для логгирования или дебага, то
		 * пользователя ждет сюрприз tdv: Переопределил - на мой взгляд ничего
		 * не изменилось, тест работает по-старому. Если же речь о том, что
		 * пользователь не сможет вызвать метод newFile, определённный в текущем
		 * класса, то ему не нужно использовать "super"
		 **/
		final File tmpFile = newFile();
		newFile();

		try (FileWriter fileWriter = new FileWriter(tmpFile)) {
			fileWriter.write(ethalon);
		}
		final Value<String> value = newValue();

		final OnFileReader fileReader = new OnFileReader(tmpFile);
		/**
		 * TODO REVIEW(fdv): Скобки вокруг t избыточны tdv: Согласен.
		 **/
		fileReader.accept(t -> value.setValue(t.readLine()));
		assertEquals(ethalon, value.getValue());

	}

	// TODO (tdv): Придумать как бросать исключение при передаче null
	@Ignore
	@Test
	public void testConstructorWithNullArgument() {
		exception.expect(NullPointerException.class);
		new OnFileReader((File) null);
	}

	@Test
	public void testConstructorReturnValueIsNotNull() {
		/**
		 * TODO REVIEW(fdv): запутано. Если посмотреть комменты к
		 * createExchangePoint - то это место, где дизайн выходит боком.
		 * Объяснить сложно, логика теста запутана. Нужно обсудить словами.
		 **/
		createExchangePoint();
		final OnFileReader fileReader = new OnFileReader(exchangePoint);
		assertNotNull(fileReader);
	}

	@Ignore
	@Test
	public void testConstructorWithMissingFile() {
		exception.expectCause(isA(FileNotFoundException.class));
		/**
		 * TODO REVIEW(fdv): В любой *nix системе есть корень с именем / При
		 * работе в linux исключение FileNotFound будет звучать очень странно.
		 * Корневой каталог всегда существует. (По крайней мене, если
		 * запустилась java и maven tdv: Пока не придумал, как сделать иначе,
		 * поскольку способ "получить имя несуществующего файла и использовать
		 * его" не является атомарной операцией. В результате файл может быть
		 * создан между проверкой на существование и вызовом конструктора
		 * (следующая строка). В Linux насколько я знаю в именах файлов
		 * недопустим символ "/", но, скажем, проверять наличие файла "x/y" не
		 * имеет смысла, поскольку "x/y" будет интерпретирован как путь, который
		 * теоретически может существовать.
		 **/
		new OnFileReader(new File("/"));
	}

	@Test
	public void testExceptionHandling() {
		exception.expect(RuntimeException.class);
		execute(x -> {
			/**
			 * TODO REVIEW(fdv): RuntimveException будет только тогда, когда в
			 * сообщении будет 'Some exception'? tdv: Нет, RuntimveException
			 * будет всегда, независимо от текста.
			 **/

			throw new IOException("Совершенно произвольный текст");
		});
	}

	@AfterClass
	public static void testTmpFilesRemoval() {
		Boolean exists = false;
		/**
		 * TODO REVIEW(fdv): Если закомментировать весь цикл (три следующих
		 * строки) то тест будет зеленым ) tdv: Это верно. А если
		 * закомментировать все строки, то он тоже будет зелёным. Как и любой
		 * другой тест :)
		 **/
		for (final String s : tmpFiles) {
			final Path path = Paths.get(s);
			exists |= Files.exists(path);
		}

		assertFalse(exists);
	}

	// FIXME tdv: протестировать вызов метода close
	@Test
	public void testNotExistedFileAllowed() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile_.txt"));
	}

	@Test
	public void testFileReadingWithStringBuilder() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile.txt"));
		StringBuilder stringBuilder = new StringBuilder();
		reader.accept(t -> {
			stringBuilder.append(t.readLine());
		});
		assertEquals("1", stringBuilder.toString());
	}

}