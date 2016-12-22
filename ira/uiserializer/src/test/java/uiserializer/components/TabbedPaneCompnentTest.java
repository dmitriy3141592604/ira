package uiserializer.components;

import static org.junit.Assert.assertEquals;

import org.i2g.ira.uibuilder.HTMLElements;
import org.junit.Test;

public class TabbedPaneCompnentTest extends ComponentTestBase<TabbedPaneComponent> {

	@Override
	protected TabbedPaneComponent newComponent() {
		return new TabbedPaneComponent() {

			@Override
			public void render(HTMLElements html) {
				final HTMLElements header = html.ul();
				header.li(klass(".tab-header"));
			}
		};
	}

	@Test
	public void test$001() {
		assertEquals(1, occurenceCount("//self::node()[@class = '.tab-header']"));

	}

}
