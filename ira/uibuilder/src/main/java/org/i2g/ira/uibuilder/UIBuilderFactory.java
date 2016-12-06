package org.i2g.ira.uibuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UIBuilderFactory {

	private ClassLoader classLoader = this.getClass().getClassLoader();

	private final Tag productRoot;

	private final Transformer<Method, Tag> valueTransformer;

	public UIBuilderFactory(Tag productRoot, Transformer<Method, Tag> valueTransformer) {
		this.productRoot = productRoot;
		this.valueTransformer = valueTransformer;
	}

	public <T> T create(Class<T> t) {
		final Class<?>[] interfaces = new Class<?>[] { t };

		return newProxy(productRoot, interfaces);
	}

	@SuppressWarnings("unchecked")
	private <T> T newProxy(Tag currentRoot, final Class<?>... interfaces) {
		return (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				return newProxy(currentRoot.addChield(asValueType(method, args)), method.getReturnType());
			}

		});
	}

	private Tag asValueType(Method method, Object[] args) {
		return valueTransformer.transform(method, args);
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}