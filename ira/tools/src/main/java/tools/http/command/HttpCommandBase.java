package tools.http.command;

import static utils.Quietly.quietly;

public abstract class HttpCommandBase implements HC {

	protected abstract void impl(HCContext context) throws Exception;

	@Override
	public final void accept(HCContext context) {
		quietly(() -> impl(context));
	}

}
