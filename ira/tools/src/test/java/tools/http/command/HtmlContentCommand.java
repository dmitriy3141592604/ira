package tools.http.command;

public class HtmlContentCommand extends HttpCommandBase {

	@Override
	public void applayImpl(HttpCommandContext context) {
		context.servletResponse().setContentType("text/html; charset=utf-8");
	}

}
