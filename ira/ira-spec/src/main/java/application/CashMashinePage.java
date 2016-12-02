package application;

import userstories.actions.ActionDescription;

public interface CashMashinePage extends Navigation {

	@ActionDescription("Запросить сумму товара")
	void submintSummCalculation();

	@ActionDescription("Посмотреть предварительную сумму для оплаты")
	String getCalculatedSumm();

	@ActionDescription("Внести оплату")
	void setPayment(String rubles);

	@ActionDescription("Оплатить")
	void pay();
}
