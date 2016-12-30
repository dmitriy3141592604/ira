package tools.http.command;

import java.util.function.Supplier;

import org.junit.Test;

public class SetContentCommandTest extends HttpCommandTestBase {

	@Test
	public void setContent() {
		final SetContentCommand setContentCommand = new SetContentCommand(() -> randomString);
		setContentCommand.accept(context);
		verify(responseWriter).print(randomString);
	}

	@Test(expected = NullPointerException.class)
	public void test$notNullContentSupplier() {
		new SetContentCommand((Supplier<String>) null);
	}

}
