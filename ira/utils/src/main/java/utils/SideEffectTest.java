package utils;

import static org.junit.Assert.assertEquals;
import static utils.SideEffect.withEffect;
import static utils.Value.newValue;

import org.junit.Test;

import testutils.RandomizedTest;

public class SideEffectTest implements RandomizedTest {

	@Test
	public void test$actionPerformed() {
		final String rs = randomString();
		final Value<String> value = newValue();

		withEffect(null, () -> value.setValue(rs));

		assertEquals(rs, value.getValue());
	}

}
