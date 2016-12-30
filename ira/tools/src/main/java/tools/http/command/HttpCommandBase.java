package tools.http.command;

import static utils.Quietly.quietly;

public abstract class HttpCommandBase {

	public final void apply(HttpCommandContext context) {
		quietly(() -> applayImpl(context));
	}

	protected abstract void applayImpl(HttpCommandContext context) throws Exception;

}
