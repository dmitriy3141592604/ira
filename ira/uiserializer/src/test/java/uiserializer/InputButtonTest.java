package uiserializer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import testutils.RandomizedTest;

public class InputButtonTest implements RandomizedTest {

	private HtmlInputModelBase inputButton;

	private String sb;

	@Before
	public final void setUpInputButtonTestBase() {
		inputButton = new InputButton();
		sb = randomString();
	}

	@Test
	public void test$type() {
		assertEquals("button", inputButton.getType());
	}

	@Test
	public void test$label() {
		inputButton.setLabel(sb);
		assertEquals(sb, inputButton.getLabel());
	}

}
