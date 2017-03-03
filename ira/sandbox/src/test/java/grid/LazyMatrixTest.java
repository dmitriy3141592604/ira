package grid;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import grid.LazyMatrix.LazyMatrixIterator;

public class LazyMatrixTest {

	private StringBuilder testLog;

	private final LazyMatrixIterator<String> testLogCallback = (x, y, value, isNewRow, isRowEnd) -> {
		testLog.append("[").append(x).append(",").append(y).append(",").append(value).append("]");
	};

	@Before
	public void setUpLazyMatrixTestBase() {
		testLog = new StringBuilder();
	}

	@Test
	public void test$001() {
		final LazyMatrix<String> lazyMatrix = new LazyMatrix<>(2, 2);
		lazyMatrix.iterateVertical(testLogCallback);
		assertEquals("[0,0,null][1,0,null][0,1,null][1,1,null]", testLog.toString());
	}

	@Test
	public void test$assign() {
		final LazyMatrix<String> lm = new LazyMatrix<>(1, 1);
		lm.assign(0, 0, "valuE");
		lm.iterateVertical(testLogCallback);
		assertEquals("[0,0,valuE]", testLog.toString());
	}

}
