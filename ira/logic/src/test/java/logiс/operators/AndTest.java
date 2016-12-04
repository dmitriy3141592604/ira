package logiс.operators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AndTest extends OperatorTestBase {

	@Test
	public void test() {
		assertEquals("false|and(left:false)", join());
	}

	@Test
	public void test$boothIsTrue() {
		left.setValue(true);
		right.setValue(true);
		assertEquals("true|and(left:true,right:true)", join());
	}
}
