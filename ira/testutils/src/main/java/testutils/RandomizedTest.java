package testutils;

import java.util.Random;

// TODO Добавить метод isRandomOdd - для имитации randomBoolean
public interface RandomizedTest {

	// TODO Сменить формат. Должен быть либо формат id либо hex
	default String randomString() {
		return "" + random().nextInt();
	}

	default Random random() {
		return new Random();
	}

	// FIXME Протестировать
	default Integer randomInteger() {
		return new Integer(random().nextInt());
	}
}
