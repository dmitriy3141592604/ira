package tools.http.command;

import org.junit.Test;

public class SetContentCommandTest extends HttpCommandTestBase {

	@Test
	public void setContent() {
		final SetContentCommand setContentCommand = new SetContentCommand(() -> randomString);
		setContentCommand.apply(context);
		verify(responseWriter).print(randomString);
	}

}
