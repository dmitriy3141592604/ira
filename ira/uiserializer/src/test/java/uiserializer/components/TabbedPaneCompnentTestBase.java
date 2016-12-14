package uiserializer.components;

import org.junit.Before;

public abstract class TabbedPaneCompnentTestBase extends ComponentTestBase {

	protected TabbedPaneCompnent cmp;

	@Before
	public final void setUpTabbedPaneCompnentTest() {
		// final HTMLElements html = builder.getHtml();
		// // TODO Следующая строка не ломает тесты
		// new HtmlHeadComponent().render(html);
		// cmp = new TabbedPaneCompnent(html.body());
	}

}
