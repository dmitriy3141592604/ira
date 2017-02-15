package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import utils.Behavior;

public class NodeTest extends NodeTestBase {

	@Test
	@Behavior("Для узла всегда доступна метаинформация")
	public void test$metaInformationAccessibleForNode() {
		assertNotNull(newNode("root").getMetaInfo());
	}

	@Test
	@Behavior("Узел запоминает произведенные из него узлы")
	public void test$nodeRememberOutgoingEdges() {
		final Node node = newNode("root");
		node.bindedWith(newNode("nextNode"), "edgeName");

		assertEquals("edgeName", node.edges().iterator().next().name());
	}

}
