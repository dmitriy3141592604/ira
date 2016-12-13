package application;

import userstories.actions.ActionDescription;
import utils.Translation;

public interface Navigation {

	@Translation("Касса")
	@ActionDescription("Перейти на страницу оплаты товара")
	CashMashinePage goToCashMashinePage();

	@Translation("Выбор товара")
	@ActionDescription("Перейти на страницу выбора товара")
	FoodPage jumpToFoodPage();

	@Translation("Помощ")
	@ActionDescription("Открыть помощ")
	HelpPage jumpHelpPage();

}
