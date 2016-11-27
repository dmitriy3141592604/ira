package l1.uml.diagrams.usecases;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ItemsCollector {

	@SuppressWarnings("unchecked")
	public <T> void collectFields(Object o, List<T> items, Predicate<Class<?>> p) {
		try {
			for (final Field declaredField : getDeclaredFields(o)) {
				declaredField.setAccessible(true);
				final Class<?> declaredFieldClass = declaredField.get(o).getClass();
				if (p.test(declaredFieldClass)) {
					items.add((T) declaredField.get(o));
				}
			}
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	private List<Field> getDeclaredFields(Object o) {
		final List<Field> fields = new ArrayList<Field>();

		for (final Class<?> c : getSubClassess(o.getClass())) {
			for (final Field declaredFields : c.getDeclaredFields()) {
				fields.add(declaredFields);
			}
		}
		return fields;
	}

	private List<Class<?>> getSubClassess(Class<?> c) {
		final List<Class<?>> retVal = new ArrayList<Class<?>>();
		if (c.equals(Object.class)) {
			return retVal;
		}
		retVal.add(c);
		retVal.addAll(getSubClassess(c.getSuperclass()));
		return retVal;
	}

}
