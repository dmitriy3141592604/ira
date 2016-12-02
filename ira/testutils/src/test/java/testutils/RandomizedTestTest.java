package testutils;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Test;

public class RandomizedTestTest implements RandomizedTest {

	private static final int NOT_ALLOWED_RANGE_FOR_REPEAD_VALUES = 20;

	/**
	 * Тест спорный. Предсказать будущие значнения я не могу (это генератор _случайных_ чисел).
	 * Сохранить предыдущие значения я тоже не могу (в интерфейсе сохранить историю сложно.
	 *
	 * На момент создания, вроде работает.
	 **/
	@Test
	public void test$sequenceOfIdenticalValuesNotGenerated() {
		final TreeSet<String> descriminator = new TreeSet<String>();
		for (int i = 0; i < 20; ++i) {
			descriminator.add(randomString());
		}
		assertEquals(NOT_ALLOWED_RANGE_FOR_REPEAD_VALUES, descriminator.size());
	}

}
