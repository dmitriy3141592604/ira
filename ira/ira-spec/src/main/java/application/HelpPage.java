package application;

@Name("Помощь")
public interface HelpPage extends Navigation {

	public interface HelpSearchForm {

		@Name("Строка поиска")
		NamedField searchString();

		@Name("Искать")
		NamedField search();

	}

	HelpSearchForm getHelpSearchForm();

}
