package application.support;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс именованного поля для UI")
public interface NamedField {

	@Responsibility("Отвечает за предоставление метки поля в UI")
	public String label();

	@Responsibility("Предоставляет уникальный id поля на UI")
	// TODO Нужно перенести в интерфейс Idable или IdAware
	public String id();

}
