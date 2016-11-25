package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class MethodProcessor implements InvocationHandler, Freezable {

	private final Map<String, Object> valuesHolder;

	private final Set<String> freezeLockers = new TreeSet<String>();

	private final Predicate<String> setterPredicate = new RegexpPredicate("^set[A-Z].*");

	private final Predicate<String> getterPredicate = new RegexpPredicate("^get[A-Z].*");

	public MethodProcessor(Map<String, Object> valuesHolder) {
		this.valuesHolder = valuesHolder;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		final String name = method.getName();
		final String beanFieldName = name.substring(2);
		if (setterPredicate.test(name)) {
			if (!freezeLockers.isEmpty()) {
				throw new IllegalStateException("Замороженный объект нельзя редактировать. Существуют блокировки: " + freezeLockers.toString());
			}
			final Object newValue = args[0];
			if (newValue == null) {
				throw new IllegalArgumentException("Ира против нулевых объектов");
			}
			valuesHolder.put(beanFieldName, newValue);
			return null;
		}

		if (getterPredicate.test(name)) {
			final Object oldValue = valuesHolder.get(beanFieldName);
			if (oldValue == null) {
				final Class<?> returnType = method.getReturnType();
				if (Number.class.isAssignableFrom(returnType)) {
					return (returnType.getConstructors()[0]).newInstance(-1);
				}
				return returnType.newInstance();
			}
			return oldValue;
		}
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Запрашиваемый метод: ");
		stringBuilder.append(method.getName());
		stringBuilder.append(" не поддерживается. Разрешены только геттеры и сеттеры");
		throw new IllegalArgumentException(stringBuilder.toString());

	}

	@Override
	public void freeze(String lockName) {
		if (!freezeLockers.add(lockName)) {
			final StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Блокировка ");
			stringBuilder.append(lockName);
			stringBuilder.append("уже используется.");
			throw new IllegalArgumentException(stringBuilder.toString());
		}
	}

	@Override
	public void unfreeze(String lockName) {
		freezeLockers.remove(lockName);
	}
}