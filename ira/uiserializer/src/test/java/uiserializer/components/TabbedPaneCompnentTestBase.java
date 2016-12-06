package uiserializer.components;

import org.junit.Before;

public abstract class TabbedPaneCompnentTestBase extends ComponentTestBase {

	protected TabbedPaneCompnent cmp;

	@Before
	public final void setUpTabbedPaneCompnentTest() {
		cmp = new TabbedPaneCompnent(builder.getHtml());
	}

}
