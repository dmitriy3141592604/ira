package test.uibuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.i2g.ira.uibuilder.Element;
import org.i2g.ira.uibuilder.Forest;
import org.i2g.ira.uibuilder.ForestVisitor;
import org.i2g.ira.uibuilder.Tag;
import org.junit.Test;

public class ForestTest extends ForestTestBase {

	private final class TraceVisitor implements ForestVisitor<Tag> {
		@Override
		public void begin(Tag tag) {
			tag.end(log);
		}

		@Override
		public void end(Tag tag) {
			tag.start(log);
		}
	}

	@Test
	public void test$forestCreatedFromFactory() {
		assertNotNull(root);
	}

	@Test
	public void test$rootHasValueByDefault_IN_TEST() {
		assertEquals("root", ((Element) root.getValue()).getName());
	}

	@Test
	public void test$createdChildHasSpecifiedValue() {
		assertEquals("child", ((Element) root.addChield(new Element("child")).getValue()).getName());
	}

	@Test
	public void test$createdChildIsAccesible() {
		root.addChield(new Element("newChield"));
		final Tag value = root.children().get(0).getValue();
		assertEquals("newChield", ((Element) value).getName());
	}

	@Test
	public void test$childrenAddedOnOneLevel() {
		root.addChield(new Element("a"));
		root.addChield(new Element("b"));
		root.addChield(new Element("c"));
		root.children().forEach((ch) -> log.append(((Element) ch.getValue()).getName()));

		assertEquals("abc", log.toString());
	}

	@Test
	public void test$addChildCreateNewTreeLevel() {
		final Forest<Tag> subChield = root.addChield(new Element("a")).addChield(new Element("b"));

		assertEquals("b", ((Element) subChield.getValue()).getName());
	}

	@Test
	public void test$forestSupportVisitor() {
		root.addChield(new Element("start"));
		root.addChield(new Element("sub")).addChield(new Element("subSub"));
		root.addChield(new Element("end"));

		root.visit(new TraceVisitor());

		assertEquals("<root><start></start><sub><subSub></subSub></sub><end></end></root>", log.toString());
	}

}
