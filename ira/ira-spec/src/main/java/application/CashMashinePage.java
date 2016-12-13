package application;

import userstories.actions.ActionDescription;
import utils.Translation;

/**
 * submit - это префикс для чего-то кликабельного и выполняющего действия
 *
 * @author Frolov_D
 *
 */
@Name("Касса")
public interface CashMashinePage extends Navigation {

	public static interface CashForm {
		@ActionDescription("Запросить сумму товара")
		@Translation("Сумма товара")
		void submintSummCalculation();

		@ActionDescription("Посмотреть предварительную сумму для оплаты")
		@Translation("Вычесленная сумма")
		@Name("Вычисленная сумма")
		NamedField getCalculatedSumm();

		@ActionDescription("Внести оплату")
		@Translation("Оплата")
		@Name("Оплата товара")
		NamedField getPayment();

		@ActionDescription("Оплатить")
		@Translation("Оплатить")
		void pay();
	}

	CashForm getCashForm();

	ReadOnlyValue getTitle();

}
