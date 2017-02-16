package utils.io;

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

public class OnFileReaderTestBase implements RandomizedTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected File exchangePoint;

	protected String ethalon;

	@Before
	public final void setUpOnFilereaderTestBase() throws Exception {
		ethalon = randomString();
	}

	protected static List<String> tmpFiles = new ArrayList<>();

	protected void execute(final ExceptionConsumer<BufferedReader> execute) {
		createExchangePoint();
		new OnFileReader(exchangePoint).accept(execute);
	}

	protected File createExchangePoint() {
		try {
			return exchangePoint = tmpFolder.newFile();
		} catch (final IOException e) {
			throw new RuntimeException(e);

		}
	}

	protected File newFile() throws Exception {
		final File file = tmpFolder.newFile();
		tmpFiles.add(file.getAbsolutePath());
		return file;
	}

}