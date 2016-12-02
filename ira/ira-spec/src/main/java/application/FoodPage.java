package application;

import utils.Translation;

public interface FoodPage extends Navigation {

	@Translation("Выбрать продукт")
	void selectFood(String foodName);

	@Translation("Выбрать количество продукта")
	void setAmount(int amount);

	@Translation("Подтвердить выбор")
	void submitSumm();

}
