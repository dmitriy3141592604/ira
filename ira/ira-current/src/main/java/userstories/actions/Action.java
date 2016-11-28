package userstories.actions;

import utils.Triple;

public abstract class Action {

	public Action run() {
		return this;
	}

	public abstract Triple<String, String, String> getSeleniumCode();

}
