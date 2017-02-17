package model;

import static utils.collections.Collector.newCollector;

import utils.collections.Collector;

/**
 * Узел модели, содержащий метаинформацию
 */
public class Node implements WithMarkersSupport<Node>, Comparable<Node> {

	/** Имя узла модели. **/
	private final String name;

	private final MarkerSupport markers = new MarkerSupport();

	private final Collector<Edge> edges = newCollector();

	/** Создает узел с новым именем **/
	public static Node newNode(String name) {
		return new Node(name);
	}

	/** Создает узел с новым именем **/
	public Node(String name) {
		this.name = name;
	}

	/** Имя узла **/
	public String name() {
		return name;
	}

	/**
	 * Связывает узел со следующим узлом модели
	 **/
	public Edge bindedWith(Node nextNode, String edgeName) {
		return edges.remember(new Edge(this, edgeName, nextNode));
	}

	@Override
	public MarkerSupport getMetaInfo() {
		return markers;
	}

	public Collector<Edge> edges() {
		return edges;
	}

	@Override
	public int compareTo(Node right) {
		final Node left = this;
		return left.name.compareTo(right.name);
	}

	@Override
	public boolean equals(Object rightCandidate) {
		final Node left = this;
		final Node right = (Node) rightCandidate;
		return left.name.equals(right.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}