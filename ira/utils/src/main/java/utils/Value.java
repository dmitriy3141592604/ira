package utils;

/**
 * Отвечает за хранение значения.
 * 
 * Можно использовать в тестах, для возврата значения из замыканий
 **/
// Snipet: Value<String> value = Value.newValue();
public class Value<T> {

	public static <U> Value<U> newValue(U u) {
		return new Value<U>(u);
	}

	public static <U> Value<U> newValue() {
		return newValue((U) null);
	}

	public static <U> Value<U> onceAssignedValue() {
		return new OnceAssignmentValue<U>();
	}

	private T value;

	protected Value() {
		this(null);
	}

	protected Value(T value) {
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
