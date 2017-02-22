package model;

import static utils.collections.Collector.newCollector;

import java.util.Set;
import java.util.TreeSet;

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

	/** FIXME Протестировать **/
	public Edge bindedWith(Node nextNode) {
		return edges.remember(new Edge(this, nextNode));
	}

	/** FIXME Протестировать **/
	public Node bindedTo(Node nextNode, String edgeName) {
		edges.remember(new Edge(this, edgeName, nextNode));
		return nextNode;
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

	public Set<Node> transitiveAccess() {
		final Set<Node> visitedNodes = new TreeSet<Node>();
		transitiveAccessImpl(this, visitedNodes, 0);
		return visitedNodes;
	}

	/**
	 * TODO Вынести в отдельный класс операций над узлом (this используется только для отладки)
	 */
	private void transitiveAccessImpl(Node node, final Set<Node> visitedNodes, int level) {
		final boolean isDebugEnabled = Boolean.valueOf(System.getProperty("debugOutputEnabled"));
		final int nextLevel = level + 1;
		final String c = this.getClass() + " " + "(" + level + ")" + "|  ";
		if (isDebugEnabled)
			System.out.println(c + "Start for name: " + node.name + ". Visited: " + visitedNodes);
		final boolean add = visitedNodes.add(node);
		if (add) {
			if (isDebugEnabled)
				System.out.println(c + "Register node: " + node + ". Visited now: " + visitedNodes);
			node.edges.forEach(edge -> {
				final Node targetNode = edge.getTargetNode();
				if (isDebugEnabled)
					System.out.print(c + "Is visited nodes: " + visitedNodes + " contains: " + targetNode);
				if (visitedNodes.contains(targetNode)) {
					if (isDebugEnabled)
						System.out.println(" yes");
					if (isDebugEnabled)
						System.out.println(c + "node " + targetNode + " visited in previous call");
				} else {
					if (isDebugEnabled)
						System.out.println(" no");
					if (isDebugEnabled)
						System.out.println(c + "node " + targetNode + " not visited before this call. Visit now");
					transitiveAccessImpl(targetNode, visitedNodes, nextLevel);
				}
			});
		} else {
			// TODO Судя по отладке, до сюда алгоритм не доходит. Как я понял, эта логика поглощается пустым списком узлов выше
			if (isDebugEnabled)
				System.out.println(c + "Noting");
		}
	}

	@Override
	public String toString() {
		return String.valueOf(name);
	}

}