package logic;

import static java.util.Optional.of;

import java.util.Optional;

import utils.Responsibility;

@Responsibility("Фиксирует интерфейс условия")
public interface Condition {

	default String getName() {
		throw new UnsupportedOperationException();
	}

	boolean getValue(Optional<StringBuilder> obs);

	default boolean getValue() {
		return getValue(of(new StringBuilder()));
	}

	// TODO Нужно вызывать getValue(Optional obs)
	default void run(Runnable runnable) {
		if (getValue()) {
			runnable.run();
		}
	};
}
