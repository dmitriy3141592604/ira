package tools.http.command;

public class SetHandledCommand extends HttpCommandBase {

	@Override
	public void impl(HCContext context) {
		context.baseRequest().setHandled(true);
	}

}
