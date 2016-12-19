package application.support;

import utils.Responsibility;

@Responsibility("Маркер контроллера формы")
public interface FormController {

	@Responsibility("Отвечает за создание URL обработчика формы")
	String getAction();

}
