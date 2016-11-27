package l1.uml.diagrams.usecases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ItemsCollectorTest {

	private ItemsCollector itemsCollector;

	public static class ItemsCollectorTestHelperBase {
		private final Integer bottom = new Integer(-1);

		public Integer getBottom() {
			return bottom;
		}
	}

	public static class ItemsCollectorTestHelper extends ItemsCollectorTestHelperBase {
		private final String topField = "top";

		public String getTopField() {
			return topField;
		}
	}

	@Before
	public final void setUpItemsCollectorTestBase() {
		itemsCollector = new ItemsCollector();
	}

	@Test
	public void test$collectElementsInDeclaredClass() {
		final ArrayList<String> items = new ArrayList<String>();

		itemsCollector.collectFields(new ItemsCollectorTestHelper(), items, c -> c.equals(String.class));
		assertEquals(1, items.size());
	}

	@Test
	public void test$collectElementsInBaseClass() {
		final ArrayList<String> items = new ArrayList<String>();

		itemsCollector.collectFields(new ItemsCollectorTestHelper(), items, c -> c.equals(Integer.class));
		assertEquals(1, items.size());
	}

}
