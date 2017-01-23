package application.support;

import application.WithAnnotationBasedName;
import utils.Responsibility;

@Responsibility("Маркер контроллера формы")
public interface FormController extends WithAnnotationBasedName {

	@Responsibility("Отвечает за создание URL обработчика формы")
	String getAction();

}
