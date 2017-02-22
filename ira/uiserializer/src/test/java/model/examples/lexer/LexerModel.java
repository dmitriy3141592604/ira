package model.examples.lexer;

import static utils.collections.Collector.newCollector;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TreeSet;

import org.junit.Test;

import model.Node;
import utils.collections.Collector;

public class LexerModel implements Runnable {

	private static final boolean useStdout = Boolean.valueOf(System.getProperty("useStdOut"));

	private final Collector<Node> nodes = newCollector(new TreeSet<Node>());

	@Test
	public void executor() {
		final LexerModel lexerModel = new LexerModel();
		lexerModel.run();
		try (final PrintWriter out = useStdout ? new PrintWriter(new OutputStreamWriter(System.out)) : new PrintWriter(new StringWriter());) {
			out.println("digraph lexer {");
			out.println("}");
		}

	}

	@Override
	public void run() {
		final Node start = node("Start");
		final Node end = new Node("End");
		buildModel(start, end);

	}

	private void buildModel(Node start, Node end) {
		start.bindedWith(end, "empty");

		start.bindedWith(start, "space");
	}

	private Node node(String string) {
		return nodes.remember(new Node(string));
	}

}
