package utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import utils.Responsibility;

@Responsibility("Предоставляет возможность регистрировать элементы в коллекции внутри выражения, без использования отдельного оператора")
public class Collector<T> implements Iterable<T> {

	public static <U> Collector<U> newCollector(Collection<U> storage) {
		return new Collector<U>(storage);
	}

	public static <U> Collector<U> newCollector() {
		return newCollector(new ArrayList<U>());
	}

	private final Collection<T> storage;

	public Collector(Collection<T> storage) {
		this.storage = storage;
	}

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

	@Override
	public String toString() {
		return storage.toString();
	}

}
