package userstories.actions;

import utils.Triple;

@ActionDescription("Проверяем имя залогиненого пользователя.")
public class CheckCurrentLoginedUserAction extends Action {

	private final String expectedName;

	public CheckCurrentLoginedUserAction(String expectedName) {
		this.expectedName = expectedName;
	}

	public Triple<String, String, String> getSeleniumCode() {
		return Triple.newTriple("assertText", "id=currentLoginedUserLogin", expectedName);
	}

}
