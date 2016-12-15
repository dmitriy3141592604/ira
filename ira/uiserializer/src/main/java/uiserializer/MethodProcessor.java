package uiserializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import application.Name;
import application.NamedField;

public class MethodProcessor implements InvocationHandler {

	private final Class<?> applicationClass;

	private final InterfaceNavigationFactory factory;

	private final HistoryItem historyItem;

	public MethodProcessor(Class<?> applicationClass, InterfaceNavigationFactory factory, HistoryItem historyItem) {
		this.applicationClass = applicationClass;
		this.factory = factory;
		this.historyItem = historyItem;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (NamedField.class.equals(method.getReturnType())) {
			final Name annotation = method.getAnnotation(Name.class);
			if (annotation == null) {
				throw new IllegalStateException("Method: " + method.getName() + " should have annotation: " + Name.class);
			}
		}

		if ("getName".equals(method.getName())) {
			final Name name = applicationClass.getAnnotation(Name.class);
			final String faultMessage = "NO_NAME_PRESENT" + " in " + applicationClass.getName();
			return name == null || name.value() == null ? faultMessage : name.value();
		}

		if (String.class.equals(method.getReturnType())) {
			final Name name = method.getAnnotation(Name.class);
			if (name != null && name.value() != null) {
				return name.value();
			}
			return historyItem.getRoot().getMethod().getAnnotation(Name.class).value();
		}

		try {
			return factory.buildFrom(method.getReturnType(), new HistoryItem(method, historyItem));
		} catch (final RuntimeException e) {
			throw new IllegalArgumentException("Can't crete proxy instance for method: " + method.getName() + " in class: fdv:???", e);
		}
	}
}