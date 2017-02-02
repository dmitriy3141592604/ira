package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

class Node extends MarkerSupport {

	private String name;

	private final Map<String, Node> children = new TreeMap<String, Node>();

	public Node() {
	}

	public Node select(String name) {
		if (children.containsKey(name)) {
			return children.get(name);
		}
		final Node node = new Node();
		node.name = name;
		children.put(name, node);
		return node;
	}

	public String name() {
		return name;
	}

}

public class MetaTreeTest extends MetaTreeTestBase {

	private Node mt;

	@Before
	public void setUpMetaTreeTestBase() {
		mt = new Node();
	}

	@Test
	public void test$selectSetNameForNextNode() {

		final Node next = mt.select("edge");

		assertEquals("edge", next.name());
	}

	@Test
	public void test$selectIsRepeatable() {
		assertSame(mt.select("edge"), mt.select("edge"));
	}

}
