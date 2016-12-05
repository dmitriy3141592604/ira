package logic;

public interface Condition {

	boolean getValue(StringBuilder log);

	// FIXME Дыра в производительности. Нужно пофиксить.
	default boolean getValue() {
		return getValue(new StringBuilder());
	}

	String getName();

	default void run(Runnable runnable) {
		if (getValue()) {
			runnable.run();
		}
	};
}
