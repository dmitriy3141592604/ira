package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class NodeTestCompareTo extends NodeTest {

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

}
