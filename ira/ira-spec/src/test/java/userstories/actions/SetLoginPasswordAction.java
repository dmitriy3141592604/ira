package userstories.actions;

import utils.Triple;

@ActionDescription("Вводит пароль")
public class SetLoginPasswordAction extends Action {

	private final String password;

	public SetLoginPasswordAction(String password) {
		this.password = password;
	}

	public Triple<String, String, String> getSeleniumCode() {
		return Triple.newTriple("type", "name=password", password);
	}

}
