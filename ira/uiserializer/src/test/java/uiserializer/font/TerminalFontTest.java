package uiserializer.font;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TerminalFontTest extends Assert {

	private TerminalFont font;

	@Before
	public final void setUpTerminalFontTestBase() {
		font = new TerminalFont("font");
	}

	@Test
	public void test$zerro() {
		assertEquals(" ****** ", font.get('0').get(0));
	}
}
