package model.examples.lexer;

import static model.examples.lexer.Action.NOTHING;
import static model.examples.lexer.Action.POP;
import static model.examples.lexer.Action.PUSH;
import static model.examples.lexer.State.END;
import static model.examples.lexer.State.START;
import static model.examples.lexer.State.TOKEN_REST;
import static model.examples.lexer.State.TOKEN_START;
import static model.examples.lexer.Transient.IS_EOF;
import static model.examples.lexer.Transient.IS_SPACE;
import static model.examples.lexer.Transient.IS_TOKEN_REST;
import static model.examples.lexer.Transient.IS_TOKEN_START;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Node;
import model.examples.lexer.Dot.ShapeTypes;
import model.processing.DeclaratorBase;
import utils.io.OnFileWriter;

public class LexerModel implements Supplier<Node> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/** TODO Declarator - общее имя. нужно подумать над его использованием **/
	public static class Declarator extends DeclaratorBase<DSL> {

		private final Logger logger = LoggerFactory.getLogger(getClass());

		@Override
		public void declare() {
			$(START, IS_EOF, END, NOTHING);
			$(START, IS_SPACE, START, NOTHING);

			$(START, IS_TOKEN_START, TOKEN_START, PUSH);
			$(TOKEN_START, IS_TOKEN_REST, TOKEN_REST, PUSH);
			$(TOKEN_REST, IS_TOKEN_REST, TOKEN_REST, PUSH);
			$(TOKEN_REST, IS_SPACE, START, POP);
			$(TOKEN_REST, IS_EOF, END, POP);
		}

		private void $(State start, Transient goingTo, State end, Action action) {
			logger.trace("Creating binding: " + start.name() + "(" + goingTo + ")" + end.name());
			dsl.bind(start, goingTo, end, action);
		}
	}

	private static final boolean useStdout = Boolean.valueOf(System.getProperty("useStdOut"));

	private Node buildModel() {
		final ClassLoader classLoader = getClass().getClassLoader();
		final Declarator declarator = new Declarator();
		final NodeFactory nodeFactory = new NodeFactory().newInstance();
		final DSLWatcher dslWatcher = new DSLWatcher(nodeFactory, classLoader);
		final DSL newInstance = dslWatcher.newInstance(DSL.class);
		declarator.setDSL(newInstance);

		declarator.declare();

		return nodeFactory.getNode(START);

	}

	@Test
	public void executor() {

		final Function<Node, String> nodeColorDetector = node -> {
			if (node.isName(START)) {
				return Dot.Colors.GREEN.toString();
			}
			if (node.isName(END)) {
				return Dot.Colors.BLACK.toString();
			}
			return Dot.Colors.BLUE.toString();
		};

		final Function<Node, ShapeTypes> nodeShapeDetector = node -> {

			if (node.isName(START)) {
				return Dot.ShapeTypes.CIRCLE;
			}

			if (node.isName(END)) {
				return Dot.ShapeTypes.DOUBLECIRCLE;
			}

			return Dot.ShapeTypes.ELLIPSE;
		};

		final LexerModel lexerModel = new LexerModel();

		final Node startNode = lexerModel.get();

		final Set<Node> nodes_ = startNode.transitiveAccess();

		final Dot dot = new Dot();

		dot.newGraph("lexer");

		nodes_.forEach(node -> dot.node(node).shape(nodeShapeDetector.apply(node)).color(nodeColorDetector.apply(node)).configured());

		nodes_.forEach(node -> node.edges().forEach(edge -> {
			final Node sourceNode = edge.getSourceNode();
			final Node targetNode = edge.getTargetNode();
			final String label = edge.name() + "/" + edge.getMetaInfo().getMarkerValue("action", "nothing");
			dot.transit(sourceNode, targetNode).label(label).configured();
		}));

		dot.complete();

		if (useStdout) {
			logger.info(dot.toString());
		}
		OnFileWriter.dumpToFile(getClass().getName() + ".dot", dot.toString());
	}

	@Override
	public Node get() {
		return buildModel();
	}

}
