package utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ProcessContextFactory {

	private final class MethodProcessor implements InvocationHandler, Freezable {

		private Map<String, Object> valuesHolder;

		private Set<String> freezeLockers = new TreeSet<String>();

		public MethodProcessor(Map<String, Object> valuesHolder) {
			this.valuesHolder = valuesHolder;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			String name = method.getName();
			String beanFieldName = name.substring(2);
			if (name.startsWith("set")) {
				if (!freezeLockers.isEmpty()) {
					throw new IllegalStateException("Замороженный объект нельзя редактировать. Существуют блокировки: "
							+ freezeLockers.toString());
				}
				Object newValue = args[0];
				if (newValue == null) {
					throw new IllegalArgumentException("Ира против нулевых объектов");
				}
				valuesHolder.put(beanFieldName, newValue);
				return null;
			}

			if (name.startsWith("get")) {
				Object oldValue = valuesHolder.get(beanFieldName);
				if (oldValue == null) {
					Class<?> returnType = method.getReturnType();
					if (Number.class.isAssignableFrom(returnType)) {
						System.out.println(returnType.getConstructors());

						return (returnType.getConstructors()[0]).newInstance(-1);
					}
					return returnType.newInstance();
				}
				return oldValue;
			}
			throw new IllegalArgumentException("");

		}

		@Override
		public void freeze(String lockName) {
			if (!freezeLockers.add(lockName)) {
				throw new IllegalArgumentException("Блокировка " + lockName + "уже используется.");
			}
			;
		}

		@Override
		public void unfreeze(String lockName) {
			freezeLockers.remove(lockName);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> clazz, HashMap<String, Object> valuesHolder) {

		final Map<String, Object> valuesHolder_ = valuesHolder;
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final Class[] interfaces = new Class[] { clazz, Freezable.class };
		final MethodProcessor methodProcessor = new MethodProcessor(valuesHolder_);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, methodProcessor);
	}

}
