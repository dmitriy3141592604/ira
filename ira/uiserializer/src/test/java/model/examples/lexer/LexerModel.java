package model.examples.lexer;

import static utils.collections.Collector.newCollector;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import model.Node;
import utils.collections.Collector;

public class LexerModel implements Runnable {

	private static final String TAB = "\t";

	private static final boolean useStdout = Boolean.valueOf(System.getProperty("useStdOut"));

	private Node startNode;

	private final Collector<Node> nodes = newCollector(new TreeSet<Node>());

	@Test
	public void executor() {
		final LexerModel lexerModel = new LexerModel();

		lexerModel.run();

		final Set<Node> nodes = lexerModel.startNode.transitiveAccess();

		final Dot dot = new Dot();

		dot.newGraph("lexer");

		nodes.forEach(node -> dot.node(node).configured());

		nodes.forEach(node -> node.edges().forEach(edge -> {
			dot.transit(edge.getSourceNode(), edge.getTargetNode()).configured();
		}));

		dot.complete();

		if (useStdout) {
			System.out.println(dot.toString());
		}
	}

	@Override
	public void run() {
		buildModel(startNode = node("Start"), new Node("End"));
	}

	private void buildModel(Node start, Node end) {
		start.bindedWith(end, "empty");
		start.bindedWith(start, "space");
	}

	private Node node(String string) {
		return nodes.remember(new Node(string));
	}

}
