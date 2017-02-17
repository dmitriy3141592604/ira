package model;

import static utils.collections.Collector.newCollector;

import utils.collections.Collector;

public class Model {

	private final Collector<Node> nodes = newCollector();

	private final Collector<Edge> edges = newCollector();

	public Collector<Node> nodes() {
		return nodes;
	}

	public Collector<Edge> edges() {
		return edges;
	}

	protected Node newNode(String nodeName) {
		return nodes.remember(Node.newNode(nodeName));

	}

	protected void newEdge(Node sourceNode, String edgeName, Node targetNode) {
		edges.remember(Edge.bind(sourceNode, edgeName, targetNode));
	}
}
