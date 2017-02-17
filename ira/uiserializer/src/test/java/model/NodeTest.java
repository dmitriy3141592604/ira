package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import utils.Behavior;

public class NodeTest extends NodeTestBase {

	private final String a = "a", b = "b";

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

	@Test
	@Behavior("Узлы с одинаковыми именами имеют одинаковый порядок сортировки")
	public void test$compareTo$equals() {
		assertEquals(a.compareTo(a), newNode(a).compareTo(newNode(a)));
	}

	@Test
	@Behavior("Узлы с разными именами сортируются по имени узла")
	public void test$compareTo$notEquals$1() {
		assertEquals(a.compareTo(b), newNode(a).compareTo(newNode(b)));
	}

	@Test
	@Behavior("Узлы с разными именами сортируются по имени узла, при перестановке аргументов")
	public void test$compareTo$notEquals$2() {
		assert (b.compareTo(a) * a.compareTo(b) < 0);
		assertEquals(b.compareTo(a), newNode(b).compareTo(newNode(a)));
	}

	@Test
	@Behavior("Узлы с одинаковыми именами равны")
	public void test$equals$equals() {
		assertEquals(a.equals(a), newNode(a).equals(newNode(a)));
	}

	@Test
	@Behavior("Узлы с разными именами не равны")
	public void test$equals$notEquals() {
		assertEquals(a.equals(b), newNode(a).equals(newNode(b)));
	}

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
