package utils;

@Responsibility(type = Value.class, value = "Обеспечивает возможность сохранить значение в локальной переменной, из анонимного класса")
public class Value<T> {
	private T value;

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
