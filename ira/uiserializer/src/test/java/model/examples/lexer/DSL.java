package model.examples.lexer;

public interface DSL {

	void bind(State startNode, Transient edgeName, State EndNode, Action edgeAction);

}