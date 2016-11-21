package test.uibuilder;

import org.i2g.ira.uibuilder.Forest;
import org.i2g.ira.uibuilder.ForestVisitor;
import org.junit.Test;

public class ForestTest extends ForestTestBase {

	private final class TraceVisitor implements ForestVisitor<String> {
		@Override
		public void begin(String value) {
			log.append(":+");
			log.append(value);
		}

		@Override
		public void endValue(String value) {
			log.append(":-");
			log.append(value);
		}
	}

	@Test
	public void test$forestCreatedFromFactory() {
		assertNotNull(root);
	}

	@Test
	public void test$rootHasValueByDefault_IN_TEST() {
		assertEquals("root", root.getValue());
	}

	@Test
	public void test$createdChildHasSpecifiedValue() {
		assertEquals("child", root.addChield("child").getValue());
	}

	@Test
	public void test$createdChildIsAccesible() {
		root.addChield("newChield");
		assertEquals("newChield", root.children().get(0).getValue());
	}

	@Test
	public void test$childrenAddedOnOneLevel() {
		root.addChield("a");
		root.addChield("b");
		root.addChield("c");
		root.children().forEach((ch) -> log.append(ch.getValue()));

		assertEquals("abc", log.toString());
	}

	@Test
	public void test$addChildCreateNewTreeLevel() {
		final Forest<String> subChield = root.addChield("a").addChield("b");

		assertEquals("b", subChield.getValue());
	}

	@Test
	public void test$forestSupportVisitor() {
		root.addChield("start");
		root.addChield("sub").addChield("subSub");
		root.addChield("end");

		root.visit(new TraceVisitor());

		assertEquals(":+root:+start:-start:+sub:+subSub:-subSub:-sub:+end:-end:-root", log.toString());
	}

}
