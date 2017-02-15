package model.examples.loginForm;

import static java.lang.reflect.Proxy.newProxyInstance;
import static model.Node.newNode;

import model.Node;

public class MethodCallRecorder {

	private final ClassLoader cl = getClass().getClassLoader();

	public <T> T recordMethodCallFor(Class<T> t, Node node) {
		return impl(t, node);
	}

	private <T> T impl(Class<T> t, Node node) {

		return t.cast(newProxyInstance(cl, new Class<?>[] { t }, (proxy, method, args) -> {
			node.bindedWith(newNode(null), method.getName());
			return impl(method.getReturnType(), node);
		}));
	}

}