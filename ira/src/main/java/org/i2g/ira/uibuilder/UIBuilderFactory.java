package org.i2g.ira.uibuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UIBuilderFactory<ValueType> {

	private final ClassLoader classLoader = this.getClass().getClassLoader();

	private final Forest<ValueType> productRoot;

	private final Transformer<Method, ValueType> valueTransformer;

	public UIBuilderFactory(Forest<ValueType> productRoot, Transformer<Method, ValueType> valueTransformer) {
		this.productRoot = productRoot;
		this.valueTransformer = valueTransformer;
	}

	public <T> T create(Class<T> t) {
		final Class<?>[] interfaces = new Class<?>[] { t };

		return newProxy(productRoot, interfaces);
	}

	@SuppressWarnings("unchecked")
	private <T> T newProxy(Forest<ValueType> currentRoot, final Class<?>... interfaces) {
		return (T) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) {
				return newProxy(currentRoot.addChield(asValueType(method, args)), method.getReturnType());
			}

		});
	}

	private ValueType asValueType(Method method, Object[] args) {
		return valueTransformer.transform(method, args);
	}
	// TODO Нужно обеспечить инжекцию класлоадера
}