package utils.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.Value.newValue;

import java.io.BufferedReader;

import org.junit.Test;

import utils.Value;

public class OnFileReaderTest extends OnFileReaderTestBase {
	/*
	 * @Test public void test$staticConstructor() { createExchangePoint();
	 * OnFileWriter.onFileWriter(exchangePoint, pw -> { }); }
	 */

	@Test
	public void testTest() {
		assertTrue(true);
	}

	String line;

	@Test
	public void testFileReading() throws Exception {

		final Value<String> value = newValue();

		execute(new ExceptionConsumer<BufferedReader>() {

			@Override
			public void accept(BufferedReader t) throws Exception {
				value.setValue(t.readLine());
			}
		});

		assertEquals("11111111", value.getValue());

		//
		// try (final BufferedReader reader = new BufferedReader(new
		// FileReader(exchangePoint))) {
		// value.setValue(reader.readLine());
		// }
		//
		// assertEquals("", value.getValue());

	}

	/** Заметим: Нет декларации исключения **/
	/*
	 * @Test public void test$exceptionHandling() {
	 *
	 * exception.expect(RuntimeException.class);
	 *
	 * execute(out -> { throw new IOException("some error"); });
	 *
	 * execute(new ExceptionConsumer<BufferedReader>() {
	 *
	 * @Override public void accept(BufferedReader t) throws Exception { throw
	 * new IOException("some error"); } }); }
	 */

	/*
	 * @Test public void test$unexpectedFileNameException() {
	 * exception.expect(RuntimeException.class); new OnFileWriter(new
	 * File("/")); }
	 *
	 * @Test public void test$WriterConstructorTest() { final StringWriter
	 * stringWriter = new StringWriter(); new
	 * OnFileWriter(stringWriter).accept(out -> out.print("hello"));
	 * assertEquals("hello", stringWriter.toString()); }
	 *
	 * @Test public void test$null() { exception.expect(RuntimeException.class);
	 * new OnFileWriter((Writer) null).accept(out -> out.print("hello"));
	 * //assertEquals("hello", stringWriter.toString()); }
	 */
}