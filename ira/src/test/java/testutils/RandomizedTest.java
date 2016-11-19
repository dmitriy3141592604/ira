package testutils;

import java.util.Random;

public interface RandomizedTest {

	default String randomString() {
		return "" + random().nextInt();
	}

	default Random random() {
		return new Random();
	}
}
