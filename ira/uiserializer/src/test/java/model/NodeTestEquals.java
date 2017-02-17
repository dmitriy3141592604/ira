package model;

import static model.Node.newNode;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class NodeTestEquals extends NodeTest {

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

}
