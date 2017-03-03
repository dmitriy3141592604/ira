package grid;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PointedGridContentTest {

	private StringBuilder testLog;

	private PointedGridContent point;

	@Before
	public void setUpPointedGridContentTest() {
		testLog = new StringBuilder();
	}

	public PointedGridContent newPoint(int x, int y, int dx, int dy) {
		return newPoint(x, y, dx, dy, "ConTent");
	}

	public PointedGridContent newPoint(int x, int y, int dx, int dy, String content) {
		return point = new PointedGridContent(x, y, dx, dy, () -> content);
	}

	@Test
	public void test$slice() {
		newPoint(1, 1, 1, 0).iterate((x, y) -> {
			testLog.append(Arrays.asList(x, y));
		});

		assertEquals("[1, 1][2, 1]", testLog.toString());
	}
}
