package application;

import userstories.actions.ActionDescription;
import utils.Translation;

public interface Navigation {

	@Translation("Касса")
	@ActionDescription("Перейти на страницу оплаты товара")
	// TODO Следующий код эквтвалентен: GET
	// http://localhost:8090/akkApplication/cashMashine.html
	CashMashinePage jumpToCashMashinePage();

	@Translation("Выбор товара")
	@ActionDescription("Перейти на страницу выбора товара")
	FoodPage jumpToFootPage();

	@Translation("Помощ")
	@ActionDescription("Открыть помощ")
	HelpPage jumpHelpPage();

}
