package uiserializer;

import java.lang.reflect.Method;

public class HistoryItem {

	private Class<?> klass;

	private Method method;

	private final HistoryItem root;

	public HistoryItem(Class<?> applicationClass, HistoryItem historyItem) {
		klass = applicationClass;
		this.root = historyItem;
	}

	public HistoryItem(Method method2, HistoryItem historyItem) {
		this.method = method2;
		root = historyItem;
	}

	public Class<?> getKlass() {
		return klass;
	}

	public Method getMethod() {
		return method;
	}

	public HistoryItem getRoot() {
		return root;
	}

}