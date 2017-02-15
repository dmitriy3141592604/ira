package utils.io;

import static utils.Safer.safe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import testutils.RandomizedTest;

public abstract class OnFileWriterTestBase implements RandomizedTest {

	@Rule
	public TemporaryFolder tmpFolder = new TemporaryFolder();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected String marker;

	protected String fileName;

	protected File exchangePoint;

	protected String exchangePointAbsolutePath;

	@Before
	public final void setUpOnFileWriterTestBase() {
		marker = randomString();
		fileName = randomString();
	}

	protected void execute(ExceptionConsumer<PrintWriter> execute) {
		new OnFileWriter(createExchangePoint()).accept(execute);
	}

	protected File createExchangePoint() {
		return safe(() -> {
			exchangePoint = tmpFolder.newFile(fileName);
			exchangePointAbsolutePath = exchangePoint.getAbsolutePath();
			return exchangePoint;
		});
	}

	protected String readFirstLineFromExchangePoint() {
		return safe(() -> {
			try (final BufferedReader reader = new BufferedReader(new FileReader(exchangePoint))) {
				return reader.readLine();
			}
		});
	}

}