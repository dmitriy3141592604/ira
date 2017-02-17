package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class NodeTestEdgeProcessing extends NodeTest {
	@Test
	@Behavior("Узел запоминает произведенные из него узлы")
	public void test$nodeRememberOutgoingEdges() {
		final Node node = newNode("root");
		node.bindedWith(newNode("nextNode"), "edgeName");

		assertEquals("edgeName", node.edges().iterator().next().name());
	}

}
