package application;

import application.support.FormAction;
import application.support.FormController;
import application.support.FormControllerName;
import application.support.Name;
import application.support.NamedField;
import application.support.ReadOnlyValue;
import userstories.actions.ActionDescription;
import utils.Translation;

/**
 * submit - это префикс для чего-то кликабельного и выполняющего действия
 *
 * @author Frolov_D
 *
 */
@Name("Касса")
public interface CashMashinePage extends Navigation, NameAware {

	@FormControllerName(CashFormController.class)
	public static interface CashForm extends FormController {

		@ActionDescription("Запросить сумму товара")
		@Translation("Сумма товара")
		@Name("Отправить запрос на вычислении суммы товара")
		FormAction submintSummCalculation();

		@ActionDescription("Посмотреть предварительную сумму для оплаты")
		@Translation("Вычесленная сумма")
		@Name("Вычисленная сумма")
		NamedField getCalculatedSumm();
	}

	public static interface PaymentForm {

		@ActionDescription("Внести оплату")
		@Translation("Оплата")
		@Name("Оплата товара")
		NamedField getPayment();

		@ActionDescription("Оплатить")
		@Translation("Оплатить")
		void pay();
	}

	@Name("Выбор CashForm")
	CashForm getCashForm();

	PaymentForm getPaymentForm();

	ReadOnlyValue getTitle();

}
