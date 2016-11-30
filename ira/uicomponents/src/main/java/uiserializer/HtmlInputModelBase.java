package uiserializer;

import utils.Responsibility;

@Responsibility("Реализует общее поведение для input элементов")
public abstract class HtmlInputModelBase implements HtmlInputModel {

	private String label;

	@Override
	public abstract String getType();

	@Override
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
