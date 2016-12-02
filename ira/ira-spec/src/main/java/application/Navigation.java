package application;

import userstories.actions.ActionDescription;
import utils.Translation;

public interface Navigation {

	@Translation("Касса")
	@ActionDescription("Перейти на страницу оплаты товара")
	CashMashinePage jumpToCashMashinePage();

	//	@Translation("Выбор товара")
	//	@ActionDescription("Перейти на страницу выбора товара")
	//	FoodPage jumpToFootPage();

}
