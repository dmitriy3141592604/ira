package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Safer.safe;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;

public class SaferTest implements RandomizedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	@Behavior("При нормальной работе возвращает значение из замыкания")
	public void test$positive$noException() {
		final String r = randomString();
		assertEquals(r, safe(() -> r));
	}

	@Test
	@Behavior("В исключительных ситуациях, оборачивает контролируемые исключени в RunTimeException")
	public void test$wrapException() {
		exception.expect(RuntimeException.class);
		safe(() -> {
			throw new IOException();
		});
	}

	@Test
	@Behavior("Заглушка для эммы, проверяем, что дефолтный конструктор не взрывается")
	public void test$forEmma() {
		assertNotNull(new Safer());
	}

	@Test
	public void test$noWrapRuntimeException() {
		exception.expect(IllegalStateException.class);
		safe(() -> {
			throw new IllegalStateException();
		});
	}

}
