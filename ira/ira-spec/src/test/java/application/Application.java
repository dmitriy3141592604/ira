package application;

public class Application {

	/**
	 * page_name order visible CashMashinePage 1 0 ShowCasePage 2 0
	 */

	private final CashMashinePage cashMashinePage = new CashMashinePage();

	private final ShowCasePage showCasePage = new ShowCasePage();

	public CashMashinePage getCashMashinePage() {
		return cashMashinePage;
	}

	public ShowCasePage getShowCasePage() {
		return showCasePage;
	}
}
