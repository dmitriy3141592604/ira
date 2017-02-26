package model.examples.lexer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Edge;
import model.Node;

// FIXME Протестировать
public class DSLWatcher {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final NodeFactory nodeFactory;

	private final ClassLoader classLoader;

	private final NodeResolver startNodeResolver = this::startNodeResolver;

	private final NodeResolver endNodeResolver = this::endNodeResolver;

	private final EdgeResolver edgeResolver = this::edgeResolver;

	public DSLWatcher(NodeFactory nodeFactory, ClassLoader classLoader) {
		this.nodeFactory = nodeFactory;
		this.classLoader = classLoader;
	}

	@SuppressWarnings("unchecked")
	public <DSLType> DSLType newInstance(Class<DSLType> dsl) {
		return (DSLType) Proxy.newProxyInstance(classLoader, new Class<?>[] { dsl }, new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				final Node startNode = startNodeResolver.apply(proxy, method, args, nodeFactory);
				final Node endNode = endNodeResolver.apply(proxy, method, args, nodeFactory);
				edgeResolver.apply(proxy, method, args, startNode, endNode).mark("action", args[3].toString());

				logger.trace("[{};{}]", startNode.name(), endNode.name());

				final Class<?> returnType = method.getReturnType();
				if ("void".equals(returnType.getName())) {
					return null;
				}
				return newInstance(returnType);
			}
		});
	}

	private Node startNodeResolver(Object proxy, Method method, Object[] args, NodeFactory nodeFactory) {
		return nodeFactory.getNode(args[0].toString());
	}

	private Node endNodeResolver(Object proxy, Method method, Object[] args, NodeFactory nodeFactory) {
		return nodeFactory.getNode(args[2].toString());
	}

	private Edge edgeResolver(Object proxy, Method method, Object[] args, Node startNode, Node endNode) {
		return startNode.bindedWith(endNode, args[1].toString());
	}
}
