package utils.collections;

import java.util.Collection;
import java.util.Iterator;

import utils.Responsibility;

@Responsibility("Предоставляет возможность регистрировать элементы в коллекции внутри выражения, без использования отдельного оператора")
public class Collector<T> implements Iterable<T> {

	public static class CollectorException extends RuntimeException {

		private static final long serialVersionUID = -861083677160288392L;

		public CollectorException(String message) {
			super(message);
		}

	}

	public static <U> Collector<U> newCollector(Collection<U> storage) {
		return new Collector<U>(storage);
	}

	private final Collection<T> storage;

	public Collector(Collection<T> storage) {
		this.storage = storage;
	}

	/**  Идея метода в том, что дальнейшая работа ведется с добавленным элементом.
	 * 	 Если он в коллекцию не добавлен, то это ошибочное поведение дальнейшего кода
	 *
	 * */
	public <U extends T> U remember(U newItem) {
		if (storage.add(newItem)) {
			return newItem;
		}
		throw new CollectorException("Объект: " + String.valueOf(newItem) + " в коллекцию не добавлен");
	}

	public Collection<T> getStorage() {
		return storage;
	}

	@Override
	public Iterator<T> iterator() {
		return storage.iterator();
	}

}
