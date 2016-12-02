package application;

import userstories.actions.ActionDescription;
import utils.Translation;

/**
 * submit - это префикс для чего-то кликабельного и выполняющего действия
 *
 * @author Frolov_D
 *
 */
public interface CashMashinePage extends Navigation {

	@ActionDescription("Запросить сумму товара")
	@Translation("Сумма товара")
	/** void - нет перехода **/
	void submintSummCalculation();

	@ActionDescription("Посмотреть предварительную сумму для оплаты")
	@Translation("Вычесленная сумма")
	String getCalculatedSumm();

	@ActionDescription("Внести оплату")
	@Translation("Оплата")
	void setPayment(String rubles);

	@ActionDescription("Оплатить")
	@Translation("Оплатить")
	// TODO Применить соглашение об именовании
	void pay();
}
