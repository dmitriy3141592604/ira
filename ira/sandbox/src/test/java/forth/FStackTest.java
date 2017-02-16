package forth;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FStackTest {

	private FStack fStack;

	@Before
	public final void setUpFStatckTest() {
		fStack = new FStack();
	}

	@Test
	public void test() {
		fStack.push(new FString("Hello, "));
		fStack.push(new FString("World!"));

		final FToken world = fStack.pop();
		final FToken hello = fStack.pop();

		assertEquals("Hello, World!", "" + hello + world);
	}

}
