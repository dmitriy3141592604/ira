package utils;

/**
	Отвечает за хранение значения.

	Можно использовать в тестах, для возврата
	значения из замыканий
**/
public class Value<T> {

	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
