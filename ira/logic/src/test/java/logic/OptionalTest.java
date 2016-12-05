package logic;

import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;

public class OptionalTest {

	public class MyService {

		public Optional<String> find(String sql) {
			return of("bar");
		}

	}

	MyService myService = new MyService();

	@Test
	public void test() {
		final Optional<String> optional = of("foo");

		final Optional<String> value = myService.find("sql");

		if (value.isPresent()) {
			final String string = value.get();
			System.out.println(string);
		}

		myService.find("sql").ifPresent(findedValue -> System.out.println("hello"));

		assertEquals(true, optional.isPresent());
	}
}
