package uiserializer;

import static utils.Safer.safe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.support.WithId;

public class InterfaceNavigationFactory extends InterfaceNavigationFactoryBase {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public <T> T buildFrom(Class<T> executedMethodOwnerClass) {
		return buildFrom(executedMethodOwnerClass, new HashMap<Method, Supplier<Object>>());
	}

	@SuppressWarnings("unchecked")
	private <T> T buildFrom(Class<T> executedMethodOwnerClass, Map<Method, Supplier<Object>> previousPredefinedMethods) {

		logger.trace("Start processing: {}", executedMethodOwnerClass);

		final List<Class<?>> executedMethodReturntypeClassList = new ArrayList<Class<?>>();
		final HashMap<Method, Supplier<Object>> predefinedMethods = new HashMap<Method, Supplier<Object>>();
		executedMethodReturntypeClassList.add(executedMethodOwnerClass);

		final InvocationHandler metodCallHandler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				if (previousPredefinedMethods.containsKey(method)) {
					return previousPredefinedMethods.get(method).get();
				}
				final Class<?> invokMethodReturnClass = method.getReturnType();

				if (invokMethodReturnClass.equals(String.class)) {
					return new String("");
				}
				if ("void".equals(invokMethodReturnClass.getName())) {
					return null;
				}
				if (WithId.class.isAssignableFrom(invokMethodReturnClass)) {
					final Method idMethod = safe(() -> invokMethodReturnClass.getMethod("id"));

					predefinedMethods.put(idMethod, () -> executedMethodOwnerClass.getSimpleName() + "." + method.getName());
				}
				return buildFrom(invokMethodReturnClass, predefinedMethods);
			}
		};

		final Class<?>[] executedMethodReturnTypeClassesArray = executedMethodReturntypeClassList.toArray(new Class<?>[0]);

		return (T) Proxy.newProxyInstance(classLoader, executedMethodReturnTypeClassesArray, metodCallHandler);
	}

}
