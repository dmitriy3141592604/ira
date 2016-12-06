package uiserializer.components;

import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Before;

public abstract class TabbedPaneCompnentTestBase extends ComponentTestBase {

	protected TabbedPaneCompnent cmp;

	@Before
	public final void setUpTabbedPaneCompnentTest() {
		final HTMLElements html = builder.getHtml();
		new HtmlHeadComponent(html);
		cmp = new TabbedPaneCompnent(html.body());
	}

}
