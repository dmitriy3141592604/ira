package logic;

import static java.util.Optional.of;

import java.util.Optional;

public interface Condition {

	boolean getValue(Optional<StringBuilder> obs);

	default boolean getValue() {
		return getValue(of(new StringBuilder()));
	}

	String getName();

	default void run(Runnable runnable) {
		if (getValue()) {
			runnable.run();
		}
	};
}
