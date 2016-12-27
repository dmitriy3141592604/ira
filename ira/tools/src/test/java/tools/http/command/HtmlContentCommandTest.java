package tools.http.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HtmlContentCommandTest extends HttpCommandTestBase {

	private HtmlContentCommand cmd;

	@Before
	public final void setUpHtmlContentCommandTest() {
		cmd = new HtmlContentCommand();
	}

	@Test
	public void test$HtmlContentCommand() {
		assertEquals(null, response.getContentType());

		cmd.apply(context);

		verify(response).setContentType("text/html; charset=utf-8");
	}

}
