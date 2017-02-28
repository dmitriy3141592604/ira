package model.examples.lexer;

import java.util.LinkedList;
import java.util.Queue;

import utils.Responsibility;

public class CPUImpl implements CPU {

	@Responsibility("Хранит последний обработанный символ")
	private char currentChar;

	@Responsibility("Хранит текущую позицию в потоке чтения входных файлов")
	private final TokenLocation currentLocation;

	@Responsibility("Хранит начало запоминания входной последовательности")
	private TokenLocation rememberingTokenLocation;

	@Responsibility("Хранит заполненные символы")
	private final StringBuilder rememberedChars = new StringBuilder();

	@Responsibility("Очередь для хранения выходных символов")
	private final Queue<Token> parsedTokens = new LinkedList<Token>();

	public CPUImpl() {
		this.currentLocation = new TokenLocation("*inline*", 0, 0);
		this.rememberingTokenLocation = new TokenLocation("*inline*", 0, 0);
		this.currentChar = new Character((char) 0);
	}

	@Override
	@Responsibility("Возвращает последний запомненый символ")
	public char getCurrentChar() {
		return currentChar;
	}

	@Override
	@Responsibility("Обрабатывает факт окончания чтения токена")
	public Token pop() {
		convertRememberedCharsToOutputToken();
		return parsedTokens.remove();
	}

	@Override
	@Responsibility("Обеспечиваеь обработку нового входного символа")
	public void push(char ch) {
		currentChar = ch;
		currentLocation.newChar(ch);
		if (rememberedChars.length() == 0) {
			rememberingTokenLocation = new TokenLocation(currentLocation);
		}
		rememberedChars.append(ch);
	}

	@Override
	@Responsibility("Обрабатывает новую строку")
	public void lineFeed() {
		currentLocation.lineFeed();
	}

	@Override
	@Responsibility("Обрабатывает конец входного потока")
	public void eof() {
		convertRememberedCharsToOutputToken();
	}

	@Override
	@Responsibility("Управляет именованием потока")
	public void newSource(String source) {
		eof();
		currentLocation.setNewPosition(source, 0, 0);
	}

	@Override
	public boolean hasNextToken() {
		return !parsedTokens.isEmpty();
	}

	private void convertRememberedCharsToOutputToken() {
		if (rememberedChars.length() == 0) {
			return;
		}
		parsedTokens.add(new Token(rememberedChars, rememberingTokenLocation));
		rememberedChars.setLength(0);
	}

}
