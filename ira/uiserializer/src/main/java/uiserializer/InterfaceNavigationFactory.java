package uiserializer;

import static java.lang.reflect.Proxy.newProxyInstance;

import application.FormController;
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

}
