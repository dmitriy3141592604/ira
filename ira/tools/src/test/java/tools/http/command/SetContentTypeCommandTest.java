package tools.http.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SetContentTypeCommandTest extends HttpCommandTestBase {

	private SetContentTypeCommand cmd;

	@Before
	public final void setUpHtmlContentCommandTest() {
		cmd = new SetContentTypeCommand();
	}

	@Test
	public void test$HtmlContentCommand() {
		assertEquals(null, response.getContentType());

		cmd.accept(context);

		verify(response).setContentType("text/html; charset=utf-8");
	}

}
