package application;

import application.support.FormController;
import application.support.FormControllerName;
import application.support.Name;
import application.support.NamedField;

@Name("Помощь")
public interface HelpPage extends Navigation {

	@FormControllerName(HelpSearchFormController.class)
	public interface HelpSearchForm extends FormController {

		@Name("Строка поиска")
		NamedField searchString();

		@Name("Искать")
		NamedField search();

	}

	@Name("Получение доступа до формы поиска помощи")
	HelpSearchForm getHelpSearchForm();

}
