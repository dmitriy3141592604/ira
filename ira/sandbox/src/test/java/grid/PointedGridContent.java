package grid;

import java.util.Arrays;
import java.util.function.Supplier;

public class PointedGridContent {

	public interface PointIterator {
		void accept(int x, int y);
	}

	public final int x, y, dx, dy;

	private final Supplier<String> content;

	/**
	 * Координаты ячейки. Начинаются с 0
	 *
	 * @param x
	 *            (min 0)
	 * @param y
	 *            (min 0)
	 * @param dx
	 *            Количество захватов вправо. 0 - не захватывает ячейки справа.
	 * @param dy
	 * @param content
	 */
	public PointedGridContent(int x, int y, int dx, int dy, Supplier<String> content) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.content = content;
	}

	@Override
	public String toString() {

		return "PGC{" + Arrays.asList(x, y, dx, dy) + "}";
	}

	public void iterate(PointIterator pi) {
		for (int i = 0; i < dy + 1; ++i) {
			for (int j = 0; j < dx + 1; ++j) {
				pi.accept(j + x, i + y);
			}
		}
	}

	public Supplier<String> getContent() {
		// System.out.println("content = " + content.get());
		return content;
	}

}