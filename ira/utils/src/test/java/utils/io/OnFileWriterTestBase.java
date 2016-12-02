package utils.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import testutils.RandomizedTest;

public class OnFileWriterTestBase implements RandomizedTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected String marker;

	protected String fileName;

	protected File exchangePoint;

	@Before
	public final void setUpOnFileWriterTestBase() throws Exception {
		marker = randomString();
		fileName = randomString();

	}

	protected void execute(final ExceptionConsumer<PrintWriter> execute) {
		createExchangePoint();
		new OnFileWriter(exchangePoint).accept(execute);
	}

	protected File createExchangePoint() {
		try {
			return exchangePoint = tmpFolder.newFile(fileName);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}