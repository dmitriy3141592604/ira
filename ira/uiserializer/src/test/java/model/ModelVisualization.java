package model;

import static model.Edge.bind;
import static model.Node.newNode;
import static utils.collections.Collector.newCollector;
import static utils.io.OnFileWriter.dumpToFile;

import java.io.PrintWriter;
import java.io.StringWriter;

import utils.collections.Collector;

/**
 * Using Graphviz to generate automated system diagrams
 *
 * http://www.ibm.com/developerworks/aix/library/au-aix-graphviz/
 */
public class ModelVisualization {

	private static final String INDENT = "\t";

	public static void main(String... args) throws Exception {

		final Collector<Edge> nodes = newCollector();
		{
			final Node foo = newNode("Foo");
			final Node bar = newNode("Bar");
			final Node baz = newNode("Baz");
			final Node banana = newNode("Banana");

			nodes.remember(bind(foo, bar));
			nodes.remember(bind(bar, baz));
			nodes.remember(bind(baz, banana));
			nodes.remember(bind(banana, foo));
		}

		final StringWriter out = new StringWriter();
		{
			final PrintWriter pw = new PrintWriter(out);
			pw.println("digraph Example {");
			{
				nodes.forEach(edge -> {
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

		final String dotFileName = "foobaz.dot";
		{

			dumpToFile(dotFileName, out);
		}

		final String htmlFileName = "foobaz.html";
		final String imageFileName = "foobaz.png";
		{
			dumpToFile(htmlFileName, "<img src='" + imageFileName + "'>");
		}

		{
			final String dotUtilPath = "c:\\wks\\prg\\Graphviz\\bin\\dot.exe";
			final Process exec = Runtime.getRuntime().exec(dotUtilPath + " -Tpng -o" + imageFileName + "  " + dotFileName);
			System.exit(exec.waitFor());
		}

	}

	private static String quoted(String name) {
		return '"' + name + '"';
	}

}
