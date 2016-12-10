package org.i2g.ira.uibuilder;

import static java.lang.reflect.Proxy.newProxyInstance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UIBuilderFactory {

	private ClassLoader classLoader = this.getClass().getClassLoader();

	private final Tag root;

	private final Transformer<Method, Tag> valueTransformer;

	public UIBuilderFactory(Tag root, Transformer<Method, Tag> valueTransformer) {
		this.root = root;
		this.valueTransformer = valueTransformer;
	}

	public <T> T create(Class<T> t) {
		return newProxy(root, new Class<?>[] { t });
	}

	@SuppressWarnings("unchecked")
	private <T> T newProxy(Tag currentRoot, final Class<?>... interfaces) {
		return (T) newProxyInstance(classLoader, interfaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				return newProxy(currentRoot.addChield(valueTransformer.applay(method, args)), method.getReturnType());
			}

		});
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}