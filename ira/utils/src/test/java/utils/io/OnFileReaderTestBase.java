package utils.io;

import static utils.Safer.safe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import testutils.RandomizedTest;
import utils.ExceptionSupplier;
import utils.Safer;

public class OnFileReaderTestBase implements RandomizedTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected File exchangePoint;

	// TODO REVIEW(fdv): Переменная не используется в классе, в котрором объявлена
	// XXX REVIEW(fdv): Под рукой уже есть RandomizedTest#randomString. Вопрос: OnFileReader должен работать только с 1111?
	// tdv: Согласен. Переделал на использование RandomizedTest#randomString 
	protected String ethalon;
	
	@Before
	public final void setUpOnFilereaderTestBase() throws Exception {
		ethalon = randomString();
	}


	// TODO REVIEW(fdv): Для инициализации переменных уровня класса, есть аннотация @BeforeClass
	/** TODO REVIEW(fdv): В проекте есть класс Collector. Он как раз для случаев, когда нужна только операция add для коллекций **/
	protected static List<String> tmpFiles = new ArrayList<>();

	protected void execute(final ExceptionConsumer<BufferedReader> execute) {
		createExchangePoint();
		new OnFileReader(exchangePoint).accept(execute);
	}

	protected File createExchangePoint() {
//		tdv: Не компилируется
//		Safer.safe(() -> {
//			exchangePoint = tmpFolder.newFile();
//		});
		
		try {
			/**
			 * TODO REVIEW(fdv): Я сам так делаю, но это плохая практика. Смешиваются две операции: присваивание и возврат результата. После поиска,
			 * мест, где используется результат вызова метода, не найдено.
			 *
			 * Наброски по замечаниям:
			 *
			 * 1. create - это значить создать. О том, что это куда-то еще присваивается, из имени метода не следует.
			 *
			 * 2. Если захочется сделать два новых файла во временной директории, то пользователя этого метода ждет сюрприз.
			 *
			 * 3. Safer.safe - разработат как раз для того, что бы не писать try catch. В проекте он есть
			 *
			 */
			return exchangePoint = tmpFolder.newFile();
		} catch (final IOException e) {
			throw new RuntimeException(e);

		}
	}

	protected File newFile() throws Exception {
		System.out.println("OnFileReaderTestBase#newFile");
		/**
		 * TODO REVIEW(fdv): имя переменной f - не информативоно. Во многих случаях считается, что это функция. Для Java - замыкание.
		 * tdv: Согласен
		 **/
		final File file = tmpFolder.newFile();
		tmpFiles.add(file.getAbsolutePath());
		return file;
	}

}