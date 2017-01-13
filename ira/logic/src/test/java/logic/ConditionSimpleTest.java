package logic;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ConditionSimpleTest extends ConditionSimpleTestBase {

	@DataPoints
	public static Boolean[] boleans = { Boolean.TRUE, Boolean.FALSE };

	@Test
	public void test$name() {
		final String name = randomString();
		assertEquals(name, newSimpleCondition(name).getName());
	}

	@Theory
	public void test$value(Boolean value) {
		assertEquals(value, newCondition(randomString(), value).getValue(empty()));
	}

	@Theory
	public void test$setValue(Boolean value) {
		final ConditionSimple condition = newCondition(false);

		condition.setValue(value);

		assertEquals(value, condition.getValue(empty()));
	}

	@Theory
	public void test$setOn(Boolean value) {
		final ConditionSimple c = newCondition(value);

		c.setOn();

		assertEquals(true, c.getValue());

	}

	@Theory
	public void test$setOff(Boolean value) {
		final ConditionSimple c = newCondition(value);

		c.setOff();

		assertEquals(false, c.getValue());

	}

}
