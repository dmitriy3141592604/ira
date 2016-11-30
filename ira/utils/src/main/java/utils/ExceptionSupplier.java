package utils;

@Responsibility("Позволяет использовать в замыканиях функции, выбрасывающие контролируемые исключения без оборачивающего try catch")
public interface ExceptionSupplier<T> {

	T get() throws Exception;

	default T save() {
		try {
			return get();
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}
