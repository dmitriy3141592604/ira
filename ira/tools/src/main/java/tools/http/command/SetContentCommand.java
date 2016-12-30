package tools.http.command;

import java.util.function.Supplier;

public class SetContentCommand extends HttpCommandBase {

	private final Supplier<String> contentFactory;

	public SetContentCommand(Supplier<String> contentFactory) {
		if (null == contentFactory) {
			throw new NullPointerException("content factory is null. Not allowed.");
		}
		this.contentFactory = contentFactory;
	}

	@Override
	public void impl(HCContext context) throws Exception {
		context.servletResponse().getWriter().print(contentFactory.get());
	}

}
