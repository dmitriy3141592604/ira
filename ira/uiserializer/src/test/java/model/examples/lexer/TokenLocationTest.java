package model.examples.lexer;

import static org.junit.Assert.assertEquals;

import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;

import testutils.MutableAssert;

public class TokenLocationTest extends MutableAssert {

	private TokenLocation tl;

	private final Supplier<String> tlString = () -> tl.toString();

	@Before
	public final void setUpTokenLocationTestBase() {
		tl = new TokenLocation("*inline*", 1, 1);
	}

	@Test
	public void test$toString() {
		assertEquals("Token{*inline*[1:1]}", tl.toString());
	}

	@Test
	public void test$charAppending() {
		assertMutation("Token{*inline*[1:1]}", "Token{*inline*[1:2]}", tlString, () -> tl.newChar('a'));
	}

	@Test
	public void test$lineFeed() {
		assertMutation("Token{*inline*[1:1]}", "Token{*inline*[2:1]}", tlString, () -> {
			tl.newChar('a');
			tl.lineFeed();
			tl.newChar('b');
		});
	}

	@Test
	public void test$copyConstructor() {
		assertEquals("Token{fileName[2:3]}", new TokenLocation(new TokenLocation("fileName", 2, 3)).toString());
	}

}
