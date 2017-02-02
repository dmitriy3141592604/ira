package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static structure.Edge.bind;

import org.junit.Test;

import utils.Behavior;

public class NodeTest extends NodeTestBase {

	@Test
	@Behavior("Узлы можно связывать переходами")
	public void test$selectSetNameForNextNode() {
		final Edge edge = bind(newNode("root"), "edge", newNode("target"));

		assertEquals("edge", edge.getName());
	}

	@Test
	@Behavior("Для узла всегда доступна метаинформация")
	public void test$metaInformationAccessibleForNode() {
		assertNotNull(newNode("root").getMetaInfo());
	}

}
