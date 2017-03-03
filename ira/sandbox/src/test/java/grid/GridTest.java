package grid;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import utils.io.OnFileWriter;

public class GridTest extends GridTestBase {

	private int gridWidth;
	private int gridHeight;

	@Before
	public void setUpGridTest() {
		gridWidth = 5;
		gridHeight = 5;
	}

	@Test
	public void test$2to2() {
		$(1, 1, 1, 1, span("1.1"));
		$(2, 1, 1, 1, span("2.1"));
		$(1, 2, 1, 1, span("1.2"));
		$(2, 2, 1, 1, span("2.2"));

		OnFileWriter.dumpToFile("c:\\wks\\tmp\\grid-2to2.html", getGrid());
	}

	@Test
	public void test$row() {
		$(1, 1, 5, 1, span("1.1"));
		$(1, 2, 1, 4, span("1.2"));
		$(2, 2, 1, 1, span("2.2"));
		$(5, 5, 1, 1, "Send");
		$(4, 5, 1, 1, "Cancel");
		$(2, 5, 2, 1, "");

		OnFileWriter.dumpToFile("c:\\wks\\tmp\\grid-11.html", getGrid());
	}

	@Test
	@Ignore
	public void test$2textArea() {
		gridWidth = 7;
		gridHeight = 3;
		$(1, 1, 1, 1, span("22:37"));
		$(2, 1, 5, 1, span("<input/>"));
		$(7, 1, 1, 1, span("GOOD"));

		OnFileWriter.dumpToFile("c:\\wks\\tmp\\textArea.html", getGrid());
	}

	private String getGrid() {
		final StringBuilder out = new StringBuilder();

		final LazyMatrix<Cell> lazyMatrix = new LazyMatrix<>(gridWidth, gridHeight);
		lazyMatrix.iterateVertical((x, y, value, isRowStart, isRowEnd) -> {
			lazyMatrix.assign(x, y, new Cell(""));
		});

		points.forEach(p -> {
			p.iterate((x, y) -> {
				lazyMatrix.assign(x, y, null);
			});
			lazyMatrix.assign(p.x, p.y, new Cell(p.dx, p.dy, p.getContent().get()));
		});
		out.append("<table border='1'>");
		out.append("\n");

		lazyMatrix.iterateVertical((x, y, cell, isRowStart, isRowEnd) -> {
			if (isRowStart) {
				out.append("<tr>");
			}
			if (cell != null) {
				out.append("<td colspan='" + cell.getColspan() + "' rowspan='" + cell.getRowspan() + "'>");
				out.append(cell.getContent());
				out.append("</td>");
			}
			if (isRowEnd) {
				out.append("</tr>");
				out.append("\n");
			}
		});
		out.append("</table>");
		out.append("\n");
		return out.toString();
	}

	protected void print(Object... objects) {
		for (final Object object : objects) {
			System.out.print(String.valueOf(object));
			System.out.print(" ");
		}
		System.out.println();

	}

	private String span(String string) {
		return string;
	}

	public void $(int x, int y, int dx, int dy, String content) {

		points.add(new PointedGridContent(x - 1, y - 1, dx - 1, dy - 1, () -> content));
	}
}
