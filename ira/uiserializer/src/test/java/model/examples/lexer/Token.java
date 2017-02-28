package model.examples.lexer;

import static java.util.Objects.requireNonNull;

import utils.Responsibility;

public class Token extends TokenLocation {

	@Responsibility("Хранит значение токена")
	private final String value;

	public Token(CharSequence value, TokenLocation location) {
		super(location);
		requireNonNull(value, "Input char sequence must be not null");
		this.value = value.toString();
	}

	public String getValue() {
		return value;
	}

}