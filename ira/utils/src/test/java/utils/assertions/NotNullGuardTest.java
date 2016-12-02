package utils.assertions;

import static org.junit.Assert.assertNotNull;
import static utils.assertions.NotNullGuard.guard;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;

public class NotNullGuardTest implements RandomizedTest {

	private String inputValue;

	private String formatPattern;

	private Object owner;

	private Object child;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public final void setUpNotNullGuardTest() {
		inputValue = randomString();
		formatPattern = inputValue;
		owner = new Object();
		child = new Object();
	}

	@Test
	public void test$canCreateInstance() {
		assertNotNull(new NotNullGuard());
	}

	@Test
	public void test$vanila() {
		runGuard();
	}

	private String runGuard() {
		return guard(inputValue, formatPattern, owner, child);
	}

	@Test(expected = IllegalStateException.class)
	public void test$nullInputNotAllowed() {
		inputValue = null;
		runGuard();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test$nullFormatPatternNotAllowed() {
		formatPattern = null;
		runGuard();
	}

	@Test
	public void test$nullParamsAreAllowed() {
		exception.expectMessage("null_null");
		formatPattern = "%s_%s";
		owner = null;
		child = null;
		inputValue = null;
		runGuard();
	}

}
