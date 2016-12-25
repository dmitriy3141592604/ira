package uiserializer.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TabbedPaneComponentTest extends ComponentTestBase<TabbedPaneComponent> {

	@Override
	protected TabbedPaneComponent newComponent() {
		return new TabbedPaneComponent();
	}

	@Test
	public void test$headerConstructed() {
		assertEquals(1, occurenceCount("//self::node()[@class = 'tab-headers']"));
	}

	@Test
	public void test$bodyConstructed() {
		assertEquals(1, occurenceCount("//self::node()[@class = 'tab-panes']"));
	}

}
