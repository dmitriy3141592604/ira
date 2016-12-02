package utils;

@Responsibility("Предоставляет синтаксический сахар для инициализации полей класса, в случае если возможно контролируемое исключение")
public class Safer {

	public static <T> T safe(ExceptionSupplier<T> ec) {
		return ec.safe();
	}

}
