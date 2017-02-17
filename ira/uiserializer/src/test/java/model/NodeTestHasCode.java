package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class NodeTestHasCode extends NodeTest {

	@Test
	@Behavior("хеш-код узла определяетс его именем")
	public void test$hasCode$equals() {
		assertEquals(a.hashCode(), newNode(a).hashCode());
	}

	@Test
	@Behavior("Если хеш-коды имен не равны, то хеш-коды узлов не равны")
	public void test$hasCode$notEquals() {
		assert a.hashCode() != b.hashCode();
		assertEquals(false, newNode(b).hashCode() == a.hashCode());
	}

}
