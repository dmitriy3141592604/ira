package application.support;

import utils.Responsibility;

@Responsibility("Предоставляет доступ до ID объекта")
public interface WithId {

	String id();

}