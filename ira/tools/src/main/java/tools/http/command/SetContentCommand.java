package tools.http.command;

import java.util.function.Supplier;

public class SetContentCommand extends HttpCommandBase {

	private final Supplier<String> contentFactory;

	public SetContentCommand(Supplier<String> contentFactory) {
		this.contentFactory = contentFactory;
	}

	@Override
	public void applayImpl(HttpCommandContext context) throws Exception {
		context.servletResponse().getWriter().print(contentFactory.get());
	}

}
