package model.examples.lexer;

import java.lang.reflect.Method;

import model.Node;

public interface NodeResolver {

	Node apply(Object proxy, Method method, Object[] args, NodeFactory nodeFactory);

}
