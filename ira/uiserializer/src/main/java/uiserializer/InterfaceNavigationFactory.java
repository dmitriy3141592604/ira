package uiserializer;

import static java.lang.reflect.Proxy.newProxyInstance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import application.support.FormController;
import application.support.Name;
import application.support.NamedField;
import uiserializer.components.Component;

public class InterfaceNavigationFactory {

	public <T> T buildFrom(Class<T> applicationClass) {
		return buildFrom(applicationClass, null);
	}

	public <T> T buildFrom(Class<T> applicationClass, HistoryItem historyItem) {
		final Class<?>[] classes = new Class<?>[] { applicationClass, Component.class, Named.class, FormController.class };
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final HistoryItem newHistoryItem = new HistoryItem(applicationClass, historyItem);
		final MethodProcessor methodProcessor = new MethodProcessor(applicationClass, this, newHistoryItem);

		return applicationClass.cast(newProxyInstance(classLoader, classes, methodProcessor));
	}

	public static class MethodProcessor implements InvocationHandler {

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
				{
					final HistoryItem root = historyItem.getRoot();
					final Method method2 = root.getMethod();
					final Name annotation = method2.getAnnotation(Name.class);
					if (annotation == null) {
						final StringBuilder sb = new StringBuilder();
						sb.append("Expected annotation ");
						sb.append(Name.class);
						sb.append(" in method ");
						sb.append(method2);
						throw new IllegalArgumentException(sb.toString());
					}
					final String value = annotation.value();
					return value;
				}
			}

			try {
				return factory.buildFrom(method.getReturnType(), new HistoryItem(method, historyItem));
			} catch (final RuntimeException e) {
				throw new IllegalArgumentException("Can't crete proxy instance for method: " + method.getName() + " in class: fdv:???", e);
			}
		}
	}

}
