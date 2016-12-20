package application;

import application.support.Name;
import utils.Translation;

@Name("Витрина")
public interface FoodPage extends Navigation, NameAware {

	@Translation("Выбрать продукт")
	void selectFood(String foodName);

	@Translation("Выбрать количество продукта")
	void setAmount(int amount);

	@Translation("Подтвердить выбор")
	void submitSumm();

}
