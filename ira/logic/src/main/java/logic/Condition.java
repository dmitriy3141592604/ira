package logic;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.Optional;

import utils.Responsibility;

@Responsibility("Фиксирует интерфейс условия")
public interface Condition {
	// TODO Исключение нужно убрать. Скорее всего, нужно заменить на полное имя класса
	default String getName() {
		throw new UnsupportedOperationException();
	}

	boolean getValue(Optional<StringBuilder> osb);

	default boolean getValue() {
		return getValue(of(new StringBuilder()));
	}

	default void run(Optional<StringBuilder> sb, Runnable runnable) {
		if (getValue(sb)) {
			runnable.run();
		}
	};

	default void run(Runnable runnable) {
		run(empty(), runnable);
	}
}
