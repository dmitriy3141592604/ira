package forth;

import static org.junit.Assert.assertEquals;
import static utils.Quietly.quietly;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import forth.functions.FSPrint;
import forth.functions.FSSumm;
import utils.io.OnFileReader;

public class SForthTest {

	@SuppressWarnings("restriction")
	private final String lineSeparator = java.security.AccessController.doPrivileged(new sun.security.action.GetPropertyAction("line.separator"));

	private SForth sForth;

	@Rule
	public TestName testName = new TestName();

	private PrintStream printStream;

	private ByteArrayOutputStream out;

	private PrintStream originalOutput;

	private final FOperation printOperation = new FSPrint();

	private final FOperation summOperation = new FSSumm();

	@Before
	public final void setUpSForthTest() throws Exception {
		final HashMap<String, List<FToken>> memory = readMemory();
		sForth = new SForth(memory);
		out = new ByteArrayOutputStream();
		printStream = new PrintStream(out, true, "utf-8");
		originalOutput = System.out;
		System.setOut(printStream);
	}

	private HashMap<String, List<FToken>> readMemory() {
		final HashMap<String, List<FToken>> memory = new HashMap<>();
		{
			new OnFileReader(new File("functionList.fthlib")).accept(br -> {
				br.lines().forEach(originalString -> {
					if (originalString.matches("^\\s*[#]")) {
						return;
					}
					final String string = originalString.replaceAll("^.*[.]..", "");
					final String functionName = Character.toLowerCase(string.charAt(0)) + string.substring(1);
					System.out.println(testName.getMethodName() + "-" + functionName);
					quietly(() -> {
						final Class<?> operationClassName = Class.forName(originalString);
						final Object operationObject = operationClassName.newInstance();
						final FToken cast = FToken.class.cast(operationObject);
						final List<FToken> oldList = memory.get(functionName);
						if (oldList == null) {
							final ArrayList<FToken> value = new ArrayList<>();
							value.add(cast);
							memory.put(functionName, value);
						} else {
							oldList.clear();
							oldList.add(cast);
						}
					});
				});
			});
		}
		return memory;
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
	 * <code> Program: "newFunction" [ dup print print ] "Hello, From
	 * Function!!! )" newFunction
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
