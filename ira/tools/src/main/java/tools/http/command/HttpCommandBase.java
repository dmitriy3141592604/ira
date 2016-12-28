package tools.http.command;

public abstract class HttpCommandBase {

	public final void apply(HttpCommandContext context) {
		try {
			applayImpl(context);
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	protected abstract void applayImpl(HttpCommandContext context) throws Exception;

}
