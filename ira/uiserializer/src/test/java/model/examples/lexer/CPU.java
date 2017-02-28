package model.examples.lexer;

public interface CPU {

	char getCurrentChar();

	Token pop();

	void push(char ch);

	void lineFeed();

	void eof();

	void newSource(String source);

	boolean hasNextToken();

}