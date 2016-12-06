package uiserializer.components;

import org.junit.Test;

public class TabbedPaneCompnentTest extends TabbedPaneCompnentTestBase {

	@Test
	public void test$oneTab() {
		cmp.add("foo", (body) -> {
			body.text("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
		});

		serializeContent(true);
	}

	@Test
	public void test$threeTabs() {
		cmp.add("foo", (body) -> {
			body.text("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
		});
		cmp.add("bar", (body) -> {
			body.text("At vero eos et accusamus et iusto odio dignissimos d");
		});
		cmp.add("baz", (body) -> {
			body.text("Nam libero tempore, cum soluta nobis est eligendi optio");
		});

		serializeContent(true);
	}

}
