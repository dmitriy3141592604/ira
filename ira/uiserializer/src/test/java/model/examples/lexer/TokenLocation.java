package model.examples.lexer;

import java.util.Objects;

import utils.Responsibility;

@Responsibility("Хранит информацию о прочитанном токене")
public class TokenLocation {

	@Responsibility("Хранит строку, в которой прочитан токен")
	protected int line;

	@Responsibility("Хранит позицию, с которой началось чтение токена")
	protected int position;

	@Responsibility("Имя источника данных, из которого прочитан токен")
	protected String sourceName;

	public TokenLocation(String sourceName, int line, int position) {
		this.sourceName = sourceName;
		this.line = line;
		this.position = position;
	}

	@Responsibility("Копирующий конструктор")
	protected TokenLocation(TokenLocation location) {
		this(Objects.requireNonNull(location.sourceName, "Source name must be not null"), location.line, location.position);
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder(256);
		stringBuilder.append("Token{");
		stringBuilder.append(sourceName);
		stringBuilder.append("[");
		stringBuilder.append(line);
		stringBuilder.append(":");
		stringBuilder.append(position);
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}

	@Responsibility("Обратабывает новый входной символ")
	public void newChar(char c) {
		position++;
	}

	@Responsibility("Обрабатывает событие новой строки")
	public void lineFeed() {
		position = 0;
		line++;
	}

	public void setNewPosition(String sourceName, int line, int position) {
		setSourceName(sourceName);
		setLine(line);
		setPosition(position);
	}

}