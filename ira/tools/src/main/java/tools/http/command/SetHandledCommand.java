package tools.http.command;

public class SetHandledCommand extends HttpCommandBase {

	@Override
	public void apply(HttpCommandContext context) {
		context.baseRequest().setHandled(true);
	}

}
