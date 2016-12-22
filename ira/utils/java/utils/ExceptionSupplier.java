package utils;

@Responsibility("Позволяет использовать в замыканиях функции, выбрасывающие контролируемые исключения без оборачивающего try catch")
public interface ExceptionSupplier<T> {

	T get() throws Exception;

	default T safe() {
		try {
			return get();
		} catch (final Exception exception) {
			// TODO Добавить текс сообщения, что причину нужно искть в
			// оригинальном стактрейсе
			throw new RuntimeException(exception);
		}
	}

}
