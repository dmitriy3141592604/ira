package userstories;

import java.util.ArrayList;
import java.util.List;

import userstories.actions.Action;
import userstories.actions.CheckCurrentLoginedUserAction;
import userstories.actions.OpenPageAction;
import userstories.actions.SetLoginAction;
import userstories.actions.SetLoginPasswordAction;
import userstories.actions.SubmitLoginFormAction;

public class UserLoginSpec {

	private final List<Action> executedActions = new ArrayList<Action>();

	protected void checkCurrentLoginedUser(String string) {
		$(new CheckCurrentLoginedUserAction(string).run());
	}

	private void $(Action run) {
		getExecutedActions().add(run);
	}

	protected void submitLoginForm() {
		$(new SubmitLoginFormAction().run());
	}

	protected void setPassword(String string) {
		$(new SetLoginPasswordAction(string).run());
	}

	protected void setLogin(String string) {
		$(new SetLoginAction(string).run());
	}

	protected void openLoginPage() {
		$(new OpenPageAction("file:///c:/wks/tmp/tmpPage/login.html").run());
	}

	public List<Action> getExecutedActions() {
		return executedActions;
	}

}
