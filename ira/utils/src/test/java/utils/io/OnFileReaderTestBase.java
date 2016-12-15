package utils.io;

import java.io.BufferedReader;
import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import testutils.RandomizedTest;

public class OnFileReaderTestBase implements RandomizedTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// protected String marker;

	protected String fileName;

	protected File exchangePoint;

	@Before
	public final void setUpOnFileReaderTestBase() throws Exception {
		// marker = randomString();
		fileName = "c:\\Temp\\temp-data.txt";
	}

	protected void execute(final ExceptionConsumer<BufferedReader> execute) {
		createExchangePoint();
		new OnFileReader(exchangePoint).accept(execute);
	}

	protected File createExchangePoint() {
		return exchangePoint = new File(fileName);
	}

}