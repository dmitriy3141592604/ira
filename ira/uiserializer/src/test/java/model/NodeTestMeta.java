package model;

import static model.Node.newNode;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import utils.Behavior;

public class NodeTestMeta extends NodeTest {

	@Test
	@Behavior("Для узла всегда доступна метаинформация")
	public void test$metaInformationAccessibleForNode() {
		assertNotNull(newNode("root").getMetaInfo());
	}

}
