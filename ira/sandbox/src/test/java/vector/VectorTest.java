package vector;

import static org.junit.Assert.assertEquals;
import static vector.Vector.newVector;

import org.junit.Test;

public class VectorTest {

	@Test
	public void test$constructor() {
		assertEquals(newVector(2, 3), newVector(2, 3));
	}

	@Test
	public void test$summ1() {
		assertEquals(newVector(7, 10), newVector(2, 3).sum(newVector(5, 7)));
	}

	@Test
	public void test$summ2() {
		final Vector v = newVector(2, 3);
		assertEquals(newVector(6, 9), v.sum(v).sum(v));
	}

	@Test
	public void test$scalar() {
		assertEquals(newVector(6, 9), newVector(2, 3).mult(3));
	}

	@Test
	public void test$length$1() {
		assertEquals(1.0d, newVector(0, 1).length(), 0.00005);
	}

	@Test
	public void test$length$2() {
		assertEquals(1.0, newVector(1, 0).length(), 0.0005);
	}

	@Test
	public void test$toString() {
		assertEquals("[2.0,3.0,5.0]", newVector(2, 3, 5).toString());
	}

}
