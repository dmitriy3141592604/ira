package model;

/**
 * Класс представления ребра в графе модели
 */
public class Edge implements WithMarkersSupport {

	/**
	 * Имя связи между узлами
	 */
	private final String name;

	/**
	 * Исходный узел перехода
	 */
	private final Node sourceNode;

	/**
	 * Результирующий узел перехода
	 */
	private final Node targetNode;

	/**
	 * Мета информация связанная с переходом
	 */
	private final MarkerSupport markers = new MarkerSupport();

	public static Edge bind(Node sourceNode, String edgeName, Node targetNode) {
		return sourceNode.bindedWith(targetNode, edgeName);
	}

	/**
	 * Создает новый переход соединяющий два узла
	 */
	public Edge(Node sourceNode, String name, Node targetNode) {
		this.sourceNode = sourceNode;
		this.name = name;
		this.targetNode = targetNode;
	}

	/**
	 * Имя перехода между узлами
	 */
	public String getName() {
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

	/** Метаинформация о переходе **/
	@Override
	public MarkerSupport getMetaInfo() {
		return markers;
	}

}
