package logic;

import java.util.Optional;

// TODO Протестировать 1. Должно быть корректное имя условия. 2. Результат должен зависить от аргумента конструктора
class ConditionConst implements Condition {

	private final boolean constValue;

	public ConditionConst(boolean constValue) {
		this.constValue = constValue;
	}

	@Override
	// TODO optional<stringBuilder> -> optionalStringBuilder
	public boolean getValue(Optional<StringBuilder> obs) {
		return constValue;
	}

}