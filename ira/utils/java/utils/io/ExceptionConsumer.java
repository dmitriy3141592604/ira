package utils.io;

import utils.Responsibility;

@Responsibility("Обеспечивает синтаксический сахар. Можно использовать без оборачивающих try catch")
public interface ExceptionConsumer<T> {

	void accept(T t) throws Exception;

	default void safe(T t) {
		try {
			accept(t);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
