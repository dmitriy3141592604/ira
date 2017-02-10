package utils.io;

import static utils.Safer.safe;

import java.io.File;
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

	protected void execute(ExceptionConsumer<PrintWriter> execute) {
		createExchangePoint();
		new OnFileWriter(exchangePoint).accept(execute);
	}

	protected File createExchangePoint() {
		return safe(() -> exchangePoint = tmpFolder.newFile(fileName));
	}

}