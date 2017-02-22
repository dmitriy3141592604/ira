package forth;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class SForthTest {

	@SuppressWarnings("restriction")
	private final String lineSeparator = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

	private SForth sForth;

	@Rule
	public TestName testName = new TestName();

	private PrintStream printStream;

	private ByteArrayOutputStream out;

	private PrintStream originalOutput;

	@Before
	public final void setUpSForthTest() throws Exception {
		final HashMap<String, List<FToken>> memory = readMemory("functionList.fthlib");
		sForth = new SForth(memory);
		out = new ByteArrayOutputStream();
		printStream = new PrintStream(out, true, "utf-8");
		originalOutput = System.out;
		System.setOut(printStream);
	}

	private HashMap<String, List<FToken>> readMemory(String memoryFileName) {

		return SForth.readMemory(memoryFileName);
	}

	@After
	public final void tearDownSForthTest() throws Exception {
		System.setOut(originalOutput);
	}

	@Test
	public void test$001() throws Exception {
		/** <code> Program: "Hello World" "!" summ print **/
		sForth.applay(new FLexeme("\"Hello World\""));
		sForth.applay(new FLexeme("\"!\""));
		sForth.applay(new FLexeme("summ"));
		sForth.applay(new FLexeme("print"));

		assertEquals("Hello World!" + lineSeparator, new String(out.toByteArray(), "utf-8"));
	}

	@Test
	public void test$002() throws Exception {
		/** <code> Program: 2 3 summ print **/
		sForth.applay(new FLexeme("2"));
		sForth.applay(new FLexeme("3"));
		sForth.applay(new FLexeme("summ"));
		// sForth.applay(summOperation);
		sForth.applay(new FLexeme("print"));

		assertEquals("5" + lineSeparator, new String(out.toByteArray(), "utf-8"));
	}

	@Test
	public void test$dup() throws Exception {
		/** <code> Program: 1 print **/
		sForth.applay(new FLexeme("1"));
		sForth.applay(new FLexeme("dup"));
		sForth.applay(new FLexeme("print"));
		sForth.applay(new FLexeme("print"));

		assertEquals("1" + lineSeparator + "1" + lineSeparator, new String(out.toByteArray(), "utf-8"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test$notExistsOperator() {
		sForth.applay(new FLexeme("#unknown-operation#"));
	}

	/**
	 * <code> Program: "newFunction" [ dup print print ] "Hello, From Function!!! )" newFunction
	 **/
	@Test
	public void test$functionDefinition() throws Exception {
		sForth.applay(new FLexeme("\"newFunction\""));
		sForth.applay(new FLexeme("["));
		sForth.applay(new FLexeme("dup"));
		sForth.applay(new FLexeme("print"));
		sForth.applay(new FLexeme("print"));
		sForth.applay(new FLexeme("]"));
		sForth.applay(new FLexeme("\"Hello, From Function!!! )\""));
		sForth.applay(new FLexeme("newFunction"));

		final String expected = "Hello, From Function!!! )" + lineSeparator + "Hello, From Function!!! )" + lineSeparator;
		assertEquals(expected, new String(out.toByteArray(), "utf-8"));
	}

}
