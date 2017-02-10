package model.examples;

import static java.lang.System.exit;
import static model.DotContentSerializer.mainSerializeDotContent;
import static model.Edge.bind;
import static model.Node.newNode;
import static utils.collections.Collector.newCollector;

import java.io.PrintWriter;
import java.io.StringWriter;

import model.Edge;
import model.Node;
import utils.collections.Collector;

/**
 * Using Graphviz to generate automated system diagrams
 *
 * http://www.ibm.com/developerworks/aix/library/au-aix-graphviz/
 */
public class ModelVisualization {

	private static final String INDENT = "\t";

	public static void main(String... args) throws Exception {

		final Collector<Edge> edges = newCollector();
		final Collector<Node> nodes = newCollector();
		{
			final Node foo = nodes.remember(newNode("Foo"));
			final Node bar = nodes.remember(newNode("Bar"));
			final Node baz = nodes.remember(newNode("Baz"));
			final Node bat = nodes.remember(newNode("Bat"));

			edges.remember(bind(foo, bar));
			edges.remember(bind(bar, baz));
			edges.remember(bind(baz, bat));
			edges.remember(bind(bat, foo));
		}

		final StringWriter out = new StringWriter();
		{
			final PrintWriter pw = new PrintWriter(out);
			pw.println("digraph Example {");
			{
				nodes.forEach(node -> {
					pw.append(INDENT).append(node.name()).append("[shape=box]").println(";");
				});
				edges.forEach(edge -> {
					final String sourceNodeName = edge.getSourceNode().name();
					final String targetNodeName = edge.getTargetNode().name();

					pw.append(INDENT);
					pw.append(sourceNodeName);
					pw.append(" -> ");
					pw.append(targetNodeName);
					pw.append(" [label=").append(quoted(sourceNodeName + "->" + targetNodeName)).append("]");
					pw.append(";");
					pw.println();
				});
			}
			pw.println("}");
		}

		{
			System.out.println(out);
		}

		exit(mainSerializeDotContent("foobaz", out.toString()));

	}

	private static String quoted(String name) {
		return '"' + name + '"';
	}

}
