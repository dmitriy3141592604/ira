package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import utils.Behavior;
import utils.collections.Collector;

public class ModelTest extends ModelTestBase {

	@Test
	@Behavior("Всегда можно получить узлы модели")
	public void test$nodesAvailable() {
		final Collector<Node> nodes = new Model() {

		}.nodes();

		assertNotNull(nodes);
	}

	@Test
	@Behavior("Новые узлы запоминаются")
	public void test$nodesAreRemebered() {
		final Collector<Node> nodes = new Model() {

			{
				newNode(nodeName);
			}

		}.nodes();
		assertEquals(nodeName, nodes.getStorage().iterator().next().name());
	}

	@Test
	@Behavior("Всегда можно получить ребра модели")
	public void test$edgesAvailable() {
		final Collector<Edge> edges = new Model() {

		}.edges();
		assertNotNull(edges);
	}

	@Test
	@Behavior("Новые ребра запоминаются")
	public void test$edgesAreRemembered() {
		final Collector<Edge> edges = new Model() {
			{
				newEdge(newNode(nodeName), edgeName, newNode(nodeName));
			}
		}.edges();

		assertEquals(edgeName, edges.getStorage().iterator().next().name());
	}

}
