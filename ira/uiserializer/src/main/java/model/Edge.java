package model;

/** Класс представления ребра в графе модели **/
public class Edge implements WithMarkersSupport<Edge> {

	/** Имя связи между узлами **/
	private final String name;

	/** Исходный узел перехода **/
	private final Node sourceNode;

	/** Результирующий узел перехода **/
	private final Node targetNode;

	/** Мета информация связанная с переходом **/
	private final MarkerSupport markers = new MarkerSupport();

	public static Edge bind(Node sourceNode, String edgeName, Node targetNode) {
		return sourceNode.bindedWith(targetNode, edgeName);
	}

	public static Edge bind(Node sourceNode, Node targetNode) {
		return sourceNode.bindedWith(targetNode, null);
	}

	/** Создает новый переход соединяющий для узла **/
	public Edge(Node sourceNode, Node targetNode) {
		this(sourceNode, null, targetNode);
	}

	/** Создает новый переход соединяющий для узла **/
	public Edge(Node sourceNode, String name, Node targetNode) {
		this.sourceNode = sourceNode;
		this.name = name;
		this.targetNode = targetNode;
	}

	/** Имя перехода между узлами */
	public String name() {
		return name;
	}

	/** Целевой узел перехода **/
	public Node getTargetNode() {
		return targetNode;
	}

	/** Исходный узел перехода **/
	public Node getSourceNode() {
		return sourceNode;
	}

	@Override
	public MarkerSupport getMetaInfo() {
		return markers;
	}

}
