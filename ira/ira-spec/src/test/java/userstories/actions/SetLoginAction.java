package userstories.actions;

import utils.Triple;

@ActionDescription("Вводит логин")
public class SetLoginAction extends Action {

	private final String login;

	public SetLoginAction(String login) {
		this.login = login;
	}

	public Triple<String, String, String> getSeleniumCode() {
		return Triple.newTriple("type", "name=login", login);
	}

}
