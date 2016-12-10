package utils;

/**
	Отвечает за хранение значения.

	Можно использовать в тестах, для возврата
	значения из замыканий
**/
public class Value<T> {

	public static <U> Value<U> newValue(U u) {
		return new Value<U>(u);
	}

	public static <U> Value<U> newValue() {
		return newValue((U) null);
	}

	private T value;

	private Value(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	@SuppressWarnings("unchecked")
	public <U extends T> U setValue(U value) {
		return (U) (this.value = value);
	}

}
