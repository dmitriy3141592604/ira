package application;

import userstories.SpecDescription;

@SpecDescription("Базовый сценарий покупки продукта покупателем")
public class UserBayProductsVanilaSpec extends ApplicationSpec {

	public void spec() {
		final CashMashinePage cmp = a.getCashMashinePage();

		cmp.calculateOrderSumm();
		cmp.putCashIntoCashMashine(100);
		cmp.pay();

	}
}
