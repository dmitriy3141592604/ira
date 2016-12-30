package tools.http.command;

import org.junit.Test;

public class SetHandledCommandTest extends HttpCommandTestBase {

	@Test
	public void test$setHandled() {

		new SetHandledCommand().accept(context);

		verify(request).setHandled(true);
	}

}
