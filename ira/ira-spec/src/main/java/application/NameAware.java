package application;

import utils.Responsibility;

@Responsibility("Предоставляет доступ к имени объекта")
public interface NameAware {

	/**
	 * Возвращает имя объекта
	 */
	String name();

}
