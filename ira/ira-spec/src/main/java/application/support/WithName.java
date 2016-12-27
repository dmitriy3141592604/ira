package application.support;

import utils.Responsibility;

@Responsibility("Предоставляет доступ до имени объекта")
public interface WithName {

	String name();

}
