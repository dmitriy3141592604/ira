package utils;

/**
	Отвечает за хранение значения.

	Можно использовать в тестах, для возврата
	значения из замыканий
**/
public class Value<T> {

	private T value;

	public Value() {
		this(null);
	}

	public Value(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public static <U> Value<U> newValue(U u) {
		return new Value<U>(u);
	}

}
