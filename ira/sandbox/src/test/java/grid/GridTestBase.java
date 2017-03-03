package grid;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public abstract class GridTestBase {

	protected List<PointedGridContent> points;

	@Before
	public void setUpGridTestBase() {
		points = new ArrayList<>();
	}
}
