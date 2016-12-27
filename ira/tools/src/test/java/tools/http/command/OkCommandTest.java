package tools.http.command;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OkCommandTest extends HttpCommandTestBase {

	private OkCommand okCommand;

	@Before
	public final void setUpOkCommandTest() {
		okCommand = new OkCommand();
	}

	@Test
	public void test$okCommand() {
		assertEquals(0, response.getStatus());

		okCommand.apply(context);

		verify(response).setStatus(SC_OK);
	}

}
