package userstories.actions;

import utils.Triple;

@ActionDescription("Открываем страницу {pageUrl}")
public class OpenPageAction extends Action {

	private final String url;

	public OpenPageAction(String url) {
		this.url = url;
	}

	public Triple<String, String, String> getSeleniumCode() {
		return Triple.newTriple("open", url, null);
	}

}
