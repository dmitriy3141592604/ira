package uiserializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class InterfaceNavigationFactory extends InterfaceNavigationFactoryBase {

	@SuppressWarnings("unchecked")
	public <T> T buildFrom(Class<T> executedMethodOwnerClass) {
		final List<Class<?>> executedMethodReturntypeClassList = new ArrayList<Class<?>>();
		executedMethodReturntypeClassList.add(executedMethodOwnerClass);

		final InvocationHandler metodCallHandler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				final Class<?> invokeReturnObject = method.getReturnType();

				if (method.getReturnType().equals(String.class)) {
					return new String("fdv: random string: MOCK " + method.getName());
				}
				return buildFrom(invokeReturnObject);
			}
		};

		final Class<?>[] executedMethodReturnTypeClassesArray = executedMethodReturntypeClassList.toArray(new Class<?>[0]);

		return (T) Proxy.newProxyInstance(classLoader, executedMethodReturnTypeClassesArray, metodCallHandler);
	}

}
