package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

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

	// FIXME !Ignored
	@Test
	@Behavior("При повторном извлечении узла по существующему ребру, возвращается уже существующий узел")
	public void test$existsNodeReturned() {
		final Node root = newNode("root");
		final Node newNode = newNode("child");
		root.bindedTo(newNode, "edge");
		final Node bindedTo = root.bindedTo(newNode, "edge");
		assertSame(newNode, bindedTo);
	}

}
