package logic;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConditionFalseTest {

	@Test
	public void test$name() {
		assertEquals("const(false)", new ConditionFalse().getName());
	}

	@Test
	public void test$value() {
		assertEquals(false, new ConditionFalse().getValue(empty()));
	}
}
