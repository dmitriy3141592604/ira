package tools.http.command;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class OkCommandTest extends HttpCommandTestBase {

	private OkCommand cmd;

	@Before
	public final void setUpOkCommandTest() {
		cmd = new OkCommand();
	}

	@Test
	public void test$okCommand() {
		assertEquals(0, response.getStatus());

		cmd.accept(context);

		verify(response).setStatus(SC_OK);
	}

}
