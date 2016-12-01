package application.support;

import userstories.actions.ActionDescription;

public interface Navigation {

	@ActionDescription("Перейти на страницу оплаты товара")
	CashMashinePage jumpToCashMashinePage();

	@ActionDescription("Перейти на страницу выбора товара")
	FoodPage jumpToFootPage();

}
