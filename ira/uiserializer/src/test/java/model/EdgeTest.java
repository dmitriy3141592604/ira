package model;

import static model.Edge.bind;
import static model.Node.newNode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import utils.Behavior;

public class EdgeTest {

	@Test
	@Behavior("Узлы можно связывать переходами")
	public void test$selectSetNameForNextNode() {
		final Edge edge = bind(newNode("root"), "edge", newNode("target"));

		assertEquals("edge", edge.getName());
	}

	@Test
	@Behavior("У переходя всегда доступна метаинформация")
	public void test$metaInfoIsAccessible() {
		assertNotNull(bind(newNode("root"), "edge", newNode("target")).getMetaInfo());
	}

	@Test
	@Behavior("Узел реализует интерфейс поддержки маркеров")
	public void test$withMarkerSupportInterfaceImplemented() {
		assertEquals(true, WithMarkersSupport.class.isAssignableFrom(Edge.class));
	}

}
