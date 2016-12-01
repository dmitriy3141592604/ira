package application;

import userstories.actions.ActionDescription;

// TOD Сменить аннотацию
@ActionDescription("Страница Касса")
public class CashMashinePage extends Page {

	@ActionDescription("Запрос на рассчет суммы заказа")
	private final UIButton calculateSumm;

	public CashMashinePage() {
		calculateSumm = new UIButton("Посчитать сумму");
		otherSummCalculator = new UIButton();
	}

	@ActionDescription("Другой Запрос на рассчет суммы заказа")
	private final UIButton otherSummCalculator;

	@ActionDescription("Внести оплату за товар")
	private final PutCashAction putCashAction = new PutCashAction();

	@ActionDescription("Внести оплату за товар")
	private final PayAction payAction = new PayAction();

	public void calculateOrderSumm() {
		calculateSumm.run();
	}

	public void putCashIntoCashMashine(int i) {
		putCashAction.runWith(i);
	}

	public void pay() {
		payAction.run();
	}

}
