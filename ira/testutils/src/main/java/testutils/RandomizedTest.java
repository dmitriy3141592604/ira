package testutils;

import java.util.Random;

public interface RandomizedTest {

	default String randomString() {
		return "" + random().nextInt();
	}

	default boolean randomBoolean() {
		return random().nextInt(5) > 2;
	}

	default Random random() {
		return new Random();
	}
}
