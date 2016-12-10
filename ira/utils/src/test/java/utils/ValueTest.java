package utils;

import static java.lang.reflect.Modifier.isPrivate;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static utils.Value.newValue;

import org.junit.Test;

public class ValueTest {

	@Test
	public void test$allConstructorsIsPrivate() {
		final StringBuilder testLog = new StringBuilder();
		of(Value.class.getDeclaredConstructors()).forEach(c -> testLog.append(isPrivate(c.getModifiers())));
		assertEquals("true", testLog.toString());
	}

	@Test
	public void test$newValue() {
		final Object o = new Object();
		assertSame(o, newValue(o).getValue());
	}

	@Test
	public void test$newValueCreateANullStoredValue() {
		assertNull(newValue().getValue());
	}
}
