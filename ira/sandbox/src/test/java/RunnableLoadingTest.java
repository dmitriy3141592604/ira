import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Supplier;

import org.junit.Test;

import testutils.RandomizedTest;

public class RunnableLoadingTest implements RandomizedTest {

	@SuppressWarnings("serial")
	public static class Action implements Supplier<String>, Serializable {

		private final String message;

		public Action(String message) {
			this.message = message;
		}

		@Override
		public String get() {
			return message;
		}
	}

	@Test
	public void test$onFlyExecution() throws Exception {

		final String marker = randomString();

		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (final ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);) {
			objectOutputStream.writeObject(new Action(marker));
		}

		final ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		final Object readedObject = objectInputStream.readObject();
		@SuppressWarnings("unchecked")
		final Supplier<String> readObject = (Supplier<String>) readedObject;
		assertEquals(marker, readObject.get());

	}

}
