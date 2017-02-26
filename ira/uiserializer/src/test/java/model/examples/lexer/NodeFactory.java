package model.examples.lexer;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Node;

/** FIXME Протестировать **/
/** TODO Переименовать в builder **/
public class NodeFactory {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final Map<String, Node> nameToNode = new LinkedHashMap<>();

	public NodeFactory newInstance() {
		return new NodeFactory();
	}

	public Collection<Node> getNodes() {
		return nameToNode.values();
	}

	public Node getNode(Enum<?> nodeName) {
		return getNode(nodeName.toString());
	}

	public Node getNode(String nodeName) {

		final Node node = nameToNode.get(nodeName);
		if (node != null) {
			logger.trace("Return previous created node: {}", node.name());
			return node;
		}
		final Node newNode = new Node(nodeName);
		nameToNode.put(nodeName, newNode);
		logger.trace("Return new node: {}", newNode.name());
		return newNode;
	}
}
