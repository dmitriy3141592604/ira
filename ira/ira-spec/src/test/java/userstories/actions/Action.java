package userstories.actions;

import java.util.Observer;

import utils.Triple;

public abstract class Action implements NamedVariable {

	private Observer observer;

	@Override
	public void setOwner(Observer observer) {
		this.observer = observer;
	}

	public Action run() {
		observer.update(null, this);
		return this;
	}

	public Action runWith(Object object) {
		observer.update(null, this);
		return this;
	}

	public Triple<String, String, String> getSeleniumCode() {
		throw new IllegalStateException("");
	}

	@Override
	public void setVariableName(String variableName) {
	}

	@Override
	public void setActionDescription(String actionDescription) {

	};

}
