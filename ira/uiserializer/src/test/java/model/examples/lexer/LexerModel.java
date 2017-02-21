package model.examples.lexer;

import static utils.collections.Collector.newCollector;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import org.junit.Test;

import model.Edge;
import model.Node;
import model.examples.lexer.Dot.ShapeTypes;
import utils.collections.Collector;
import utils.io.OnFileWriter;

public class LexerModel implements Runnable {

	private static final boolean useStdout = Boolean.valueOf(System.getProperty("useStdOut"));

	private Node startNode;

	private final Collector<Node> nodes = newCollector(new TreeSet<Node>());

	private void buildModel() {
		final Node start = node("Start").mark("start");
		final Node end = node("End").mark("end");

		final Node tokenStart = node("TokenStart");
		final Node tokenRest = node("TokenRest");

		$(start, "isEof", end, "nothing");
		$(start, "isSpace", start, "nothing");

		$(start, "isTokenStart", tokenStart, "push");
		$(tokenStart, "isTokenRest", tokenRest, "push");
		$(tokenRest, "isTokenRest", tokenRest, "push");
		$(tokenRest, "isSpace", start, "pop");
		$(tokenRest, "isEof", end, "pop");

		startNode = start;
	}

	private Edge $(Node fromNode, String edgeName, Node toNode, String action) {
		return fromNode.bindedWith(toNode, edgeName).mark("action", action);
	}

	@Test
	public void executor() {

		final Function<Node, String> nodeColorDetector = node -> {
			return "black";
		};
		final Function<Node, ShapeTypes> nodeShapeDetector = node -> {
			if (node.getMetaInfo().hasMarker("start")) {
				return Dot.ShapeTypes.CIRCLE;
			}
			if (node.getMetaInfo().hasMarker("end")) {
				return Dot.ShapeTypes.DOUBLECIRCLE;
			}
			return Dot.ShapeTypes.ELLIPSE;
		};

		final LexerModel lexerModel = new LexerModel();

		lexerModel.run();

		final Set<Node> nodes = lexerModel.startNode.transitiveAccess();

		final Dot dot = new Dot();

		dot.newGraph("lexer");

		nodes.forEach(node -> dot.node(node).shape(nodeShapeDetector.apply(node)).color(nodeColorDetector.apply(node)).configured());

		nodes.forEach(node -> node.edges().forEach(edge -> {
			final Node sourceNode = edge.getSourceNode();
			final Node targetNode = edge.getTargetNode();
			final String label = edge.name() + "\n" + edge.getMetaInfo().getMarkerValue("action", "nothing");
			dot.transit(sourceNode, targetNode).label(label).configured();
		}));

		dot.complete();

		if (useStdout) {
			System.out.println("Output from: " + getClass());
			System.out.println(dot.toString());
		}
		OnFileWriter.dumpToFile(getClass().getName() + ".dot", dot.toString());
	}

	@Override
	public void run() {
		buildModel();
	}

	private Node node(String string) {
		return nodes.remember(new Node(string));
	}

}
