package tools.http.command;

public class SetContentTypeCommand extends HttpCommandBase {

	@Override
	public void impl(HCContext context) {
		context.servletResponse().setContentType("text/html; charset=utf-8");
	}

}
