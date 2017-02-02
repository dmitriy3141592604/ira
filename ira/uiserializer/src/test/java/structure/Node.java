package structure;

/**
 * Узел модели, содержащий метаинформацию
 */
public class Node implements WithMarkersSupport {

	/**
	 * Имя узла модели.
	 *
	 * Должно быть не нулевым.
	 **/
	private final String name;

	private final MarkerSupport markers = new MarkerSupport();

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
		return new Edge(this, edgeName, nextNode);
	}

	@Override
	public MarkerSupport getMetaInfo() {
		return markers;
	}

}