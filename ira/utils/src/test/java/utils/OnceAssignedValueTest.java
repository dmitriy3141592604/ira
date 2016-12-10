package utils;

import static utils.Value.onceAssignedValue;

import org.junit.Test;

public class OnceAssignedValueTest {

	@Test
	public void test$acceptFirstAssignment() {
		onceAssignedValue().setValue(new Object());
	}

	@Test(expected = OnceAssignmentValueException.class)
	public void test$notAlowSecondAssignment() {
		final Value<Object> oav = onceAssignedValue();
		oav.setValue(new Object());
		oav.setValue(new Object());
	}
}
