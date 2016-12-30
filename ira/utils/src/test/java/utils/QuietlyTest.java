package utils;

import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static utils.Quietly.quietly;
import static utils.Safer.safe;
import static utils.Value.newValue;

import java.io.IOException;

import org.junit.Test;

public class QuietlyTest extends QuietlyTestBase {

	@Test
	public void test$exceptionWrappedWithRuntimeException() {

		exception.expect(RuntimeException.class);
		exception.expectCause(isA(IOException.class));

		quietly(() -> {
			throw new IOException();
		});

	}

	@Test
	public void test$acionIsPerformed() {
		final Value<String> value = newValue();
		quietly(() -> value.setValue(rs));
		assertEquals(rs, value.getValue());
	}

	@Test
	public void test$runtimeExceptionNotWrapped() {
		final RuntimeException throwedException = new RuntimeException();
		final RuntimeException catchedException = safe(() -> {
			try {
				quietly(() -> {
					throw throwedException;
				});
			} catch (final RuntimeException exception) {
				return exception;
			}
			throw new AssertionError("Ожидается, что экскепшен будет проброшен из вызова наружу");
		});
		assertSame(throwedException, catchedException);
	}

}
