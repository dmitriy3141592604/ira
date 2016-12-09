package utils.collections;

import java.util.Collection;
import java.util.Iterator;

import utils.Responsibility;

@Responsibility("Предоставляет возможность регистрировать элементы в коллекции внутри выражения, без использования отдельного оператора")
public class Collector<T> implements Iterable<T> {

	public static <U> Collector<U> newCollector(Collection<U> storage) {
		return new Collector<U>(storage);
	}

	private final Collection<T> storage;

	public Collector(Collection<T> storage) {
		this.storage = storage;
	}

	public T remember(T newItem) {
		storage.add(newItem);
		return newItem;
	}

	public Collection<T> getStorage() {
		return storage;
	}

	@Override
	public Iterator<T> iterator() {
		return storage.iterator();
	}

}
