package utils.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import utils.AbstractProcess;
import utils.Behavior;
import utils.IraTest;

class OneAbstractProcess extends AbstractProcess {

}

class OtherAbstractProcess extends AbstractProcess {

}

public class AbstractProcessTest extends IraTest {

	private OneAbstractProcess one;
	private OtherAbstractProcess other;

	@Before
	public void setUpAbstractProcessTest() {
		one = new OneAbstractProcess();
		other = new OtherAbstractProcess();
	}

	@Test
	@Behavior("Класс равен самому себе")
	public void test$compareEqualsClasses() {
		assertEquals(0, one.compareTo(one));
	}
	// Returns:
	// a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.

	@Test
	public void test$less() {
		assertEquals(true, one.compareTo(other) < 0);
	}

	@Test
	public void test$greater() {
		assertEquals(true, one.compareTo(other) < 0);
	}

	@Test
	public void test$compareWithNull() {
		except(IllegalArgumentException.class);
		one.compareTo(null);
	}
}
