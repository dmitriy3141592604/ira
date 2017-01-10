package application.support;

import utils.Responsibility;

@Responsibility("Предоставляет доступ до имени объекта")
public interface WithName {

	/** TODO Нужно сделать name дефолтным. А от производных классов требовать наличие StringBuilder'а. **/
	@Responsibility("Формирует строковое имя объекта. Требований на формирование имени нет")
	String name();

}
