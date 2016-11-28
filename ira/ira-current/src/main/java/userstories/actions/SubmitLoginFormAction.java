package userstories.actions;

import utils.Triple;

@ActionDescription("Отправляет данные авторизации на сервер")
public class SubmitLoginFormAction extends Action {

	public Triple<String, String, String> getSeleniumCode() {
		return Triple.newTriple("clickAndWait", "name=submit", null);
	}
}
