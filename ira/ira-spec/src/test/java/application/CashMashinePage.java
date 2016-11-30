package application;

import java.util.ArrayList;

import userstories.actions.ActionDescription;

// TOD Сменить аннотацию
@ActionDescription("Страница Касса")
public class CashMashinePage extends Page {

	@ActionDescription("Запрос на рассчет суммы заказа")
	private final UIButton calculateSumm;

	public CashMashinePage() {
		$(calculateSumm = new UIButton("Посчитать сумму"));
		$(otherSummCalculator = new UIButton());
	}

	private void $(UIButton uiButton) {
		arrayList = new ArrayList<Object>();
		arrayList.add(uiButton);
	}

	@ActionDescription("Другой Запрос на рассчет суммы заказа")
	private final UIButton otherSummCalculator;

	@ActionDescription("Внести оплату за товар")
	private final PutCashAction putCashAction = new PutCashAction();

	@ActionDescription("Внести оплату за товар")
	private final PayAction payAction = new PayAction();

	private ArrayList<Object> arrayList;

	// @ActionDescription("Запросить на кассе сумму товара")
	public void calculateOrderSumm() {
		calculateSumm.run();
	}

	public void putCashIntoCashMashine(Rub rub) {
		putCashAction.runWith(rub);
	}

	public void pay() {
		payAction.run();
	}

}
