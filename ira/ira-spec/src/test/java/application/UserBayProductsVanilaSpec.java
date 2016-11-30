package application;

import userstories.SpecDescription;

@SpecDescription("Базовый сценарий покупки продукта покупателем")
public class UserBayProductsVanilaSpec extends ApplicationSpec {

	public void spec() {
		final CashMashinePage cmp = a.getCashMashinePage();

		cmp.calculateOrderSumm();
		cmp.putCashIntoCashMashine(rub(100));
		cmp.pay();

	}

	private Rub rub(int i) {
		return null;
	}

}
