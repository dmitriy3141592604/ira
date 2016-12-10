package utils;

import static java.lang.reflect.Modifier.isPublic;
import static java.util.stream.Stream.of;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static utils.Value.newValue;

import org.junit.Test;

import testutils.RandomizedTest;

public class ValueTest implements RandomizedTest {

	@Test
	public void test$allConstructorsIsPrivate() {
		final StringBuilder testLog = new StringBuilder();
		of(Value.class.getDeclaredConstructors()).forEach(c -> testLog.append(isPublic(c.getModifiers())));
		assertEquals("falsefalse", testLog.toString());
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

	@Test
	public void test$setValue$withDeclaredClass() {
		final Object o = new Object();
		assertSame(o, newValue().setValue(o));
	}

	@Test
	public void test$setVaueWithDerivedClass() {
		final String rs = randomString();
		final Value<Object> value = newValue();
		final String extractedValue = value.setValue(rs);
		assertSame(rs, extractedValue);
	}

}
