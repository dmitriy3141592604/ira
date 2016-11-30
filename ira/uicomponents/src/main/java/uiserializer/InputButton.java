package uiserializer;

import utils.Responsibility;

@Responsibility("Фиксирует контракт тега <input type='button'>")
public class InputButton extends HtmlInputModelBase {

	@Override
	public String getType() {
		return "button";
	}

}
