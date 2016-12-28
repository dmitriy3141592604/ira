package tools.http.command;

public class SetHandledCommand extends HttpCommandBase {

	@Override
	public void applayImpl(HttpCommandContext context) {
		context.baseRequest().setHandled(true);
	}

}
