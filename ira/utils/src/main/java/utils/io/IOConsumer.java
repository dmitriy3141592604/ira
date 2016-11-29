package utils.io;

public interface IOConsumer<T> {

	void accept(T t) throws Exception;

	default void save(T t) {
		try {
			accept(t);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
