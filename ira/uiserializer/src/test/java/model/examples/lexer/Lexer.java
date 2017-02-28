package model.examples.lexer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import model.examples.lexer.LexerModel.LexerAction;
import model.examples.lexer.LexerModel.Token;

public class Lexer implements LexerAction {

	// private final BufferedReader reader;

	// private final State state = State.START;

	// private final Map<State, Transient> transientTable = new HashMap<State, Transient>();

	// private final Map<Action, Function<Char,>>

	private final List<Character> rememberedChars = new LinkedList<Character>();

	private String newToken = null;

	// public Lexer(BufferedReader reader) {
	// this.reader = reader;
	// }

	public String nextToken() {
		return "";
	}

	public Token getToken() throws IOException {
		if (newToken != null) {
			final Token token = new Token(newToken);
			newToken = null;
			return token;
		}
		// final Character character = new Character((char) reader.read());
		// process(rememberedChars, character);
		return null;

	}

	@Override
	public void push(Character ch) {
		rememberedChars.add(ch);
	}

	@Override
	public void pop() {
		final char[] newChars = new char[rememberedChars.size()];
		for (int i = 0; i < rememberedChars.size(); ++i) {
			newChars[i] = rememberedChars.get(i);
		}
		newToken = new String(newChars);
	}
}