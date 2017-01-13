package logic;

import static org.junit.Assert.assertEquals;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ConditionConstTest extends ConditionConstTestBase {

	@DataPoints
	public static final boolean[] values = { true, false };

	@Theory
	public void test$name(boolean value) {
		assertEquals("const(" + value + ")", new ConditionConst(value).getName());
	}

}
