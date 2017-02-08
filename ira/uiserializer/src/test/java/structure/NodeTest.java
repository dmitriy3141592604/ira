package structure;

import static org.junit.Assert.assertNotNull;
import static structure.Node.newNode;

import org.junit.Test;

import utils.Behavior;

public class NodeTest extends NodeTestBase {

	@Test
	@Behavior("Для узла всегда доступна метаинформация")
	public void test$metaInformationAccessibleForNode() {
		assertNotNull(newNode("root").getMetaInfo());
	}

}
