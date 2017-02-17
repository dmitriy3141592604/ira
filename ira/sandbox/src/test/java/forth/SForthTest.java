package forth;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import forth.functions.FSPrint;
import forth.functions.FSSumm;

public class SForthTest {

	@SuppressWarnings("restriction")
	private final String lineSeparator = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

	private SForth sForth;

	private PrintStream printStream;

	private ByteArrayOutputStream out;

	private PrintStream originalOutput;

	private final FOperation printOperation = new FSPrint();

	private final FOperation summOperation = new FSSumm();

	@Before
	public final void setUpSForthTest() throws Exception {
		sForth = new SForth();
		out = new ByteArrayOutputStream();
		printStream = new PrintStream(out, true, "utf-8");
		originalOutput = System.out;
		System.setOut(printStream);
	}

	@After
	public final void tearDownSForthTest() throws Exception {
		System.setOut(originalOutput);
	}

	@Test
	public void test$001() throws Exception {
		sForth.applay(new FString("Hello World"));
		sForth.applay(new FString("!"));

		sForth.applay(summOperation);
		sForth.applay(printOperation);

		assertEquals("Hello World!" + lineSeparator, new String(out.toByteArray(), "utf-8"));
	}

	@Test
	public void test$002() throws Exception {
		sForth.applay(new FInteger(2));
		sForth.applay(new FInteger(3));
		sForth.applay(summOperation);
		sForth.applay(printOperation);

		assertEquals("5" + lineSeparator, new String(out.toByteArray(), "utf-8"));
	}

}
