package model.examples.lexer;

import static utils.collections.Collector.newCollector;

import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import org.junit.Test;

import model.Node;
import model.examples.lexer.Dot.ShapeTypes;
import utils.collections.Collector;
import utils.io.OnFileWriter;

public class LexerModel implements Runnable {

	private static final boolean useStdout = Boolean.valueOf(System.getProperty("useStdOut"));

	private Node startNode;

	private final Collector<Node> nodes = newCollector(new TreeSet<Node>());

	private void buildModel(Node start, Node end) {
		start.mark("start");
		end.mark("end");
		final Node startToken = new Node("tokenStart");
		final Node tokenRest = new Node("tokenRest");

		start.bindedWith(end, "empty");
		start.bindedWith(start, "space");

		start.bindedWith(startToken, "token-start-symbol");
		startToken.bindedWith(tokenRest, "token-rest-symbol");
		tokenRest.bindedWith(tokenRest, "token-rest-sumbol");
		tokenRest.bindedWith(end, "empty");

		tokenRest.bindedWith(start, "empty");

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
				// return Dot.ShapeTypes.POINT;
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
			dot.transit(edge.getSourceNode(), edge.getTargetNode()).label(edge.name()).configured();
		}));

		dot.complete();

		if (useStdout) {
			System.out.println(dot.toString());
		}
		OnFileWriter.dumpToFile(getClass().getName() + ".dot", dot.toString());
	}

	@Override
	public void run() {
		buildModel(startNode = node("Start"), new Node("End"));
	}

	private Node node(String string) {
		return nodes.remember(new Node(string));
	}

}
