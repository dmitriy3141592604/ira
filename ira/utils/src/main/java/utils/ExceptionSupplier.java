package utils;

import java.util.function.Supplier;

@Responsibility("Позволяет использовать в замыканиях функции, выбрасывающие контролируемые исключения без оборачивающего try catch")
public interface ExceptionSupplier<T> extends Supplier<T> {

	public static <T> Supplier<T> hideSupplierExceptions(ExceptionSupplier<T> se) {
		return se;
	}

	T create() throws Exception;

	@Override
	default T get() {
		try {
			return create();
		} catch (final Exception exception) {
			// TODO Добавить текс сообщения, что причину нужно искть в
			// оригинальном стактрейсе
			if (exception instanceof RuntimeException) {
				throw (RuntimeException) exception;
			}
			throw new RuntimeException(exception);
		}
	}

}
