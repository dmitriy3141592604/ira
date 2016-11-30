package uiserializer;

import utils.Responsibility;

@Responsibility("Фиксирует контракт html элемента input")
public interface HtmlInputModel {

	String getType();

	String getLabel();

}
