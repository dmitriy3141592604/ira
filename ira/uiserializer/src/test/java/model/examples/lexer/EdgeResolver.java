package model.examples.lexer;

import java.lang.reflect.Method;

import model.Edge;
import model.Node;

public interface EdgeResolver {

	Edge apply(Object proxy, Method method, Object[] args, Node startNode, Node endNode);
}
