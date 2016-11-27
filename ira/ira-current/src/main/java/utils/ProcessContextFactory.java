package utils;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProcessContextFactory {

	@SuppressWarnings("unchecked")
	public <T> T create(Class<T> clazz, HashMap<String, Object> valuesHolder) {
		final Map<String, Object> valuesHolder_ = valuesHolder;
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final Class<?>[] interfaces = new Class[] { clazz, Freezable.class };
		final MethodProcessor methodProcessor = new MethodProcessor(valuesHolder_);
		return (T) Proxy.newProxyInstance(classLoader, interfaces, methodProcessor);
	}

}
