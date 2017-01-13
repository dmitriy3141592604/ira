package logic;

import java.util.Optional;

public class ConditionConst implements Condition {

	private final boolean constValue;
	private final String constName;

	public ConditionConst(boolean constValue) {
		this.constValue = constValue;
		this.constName = "const(" + constValue + ")";
	}

	@Override
	public String getName() {
		return constName;
	}

	@Override
	// TODO optional<stringBuilder> -> optionalStringBuilder
	public boolean getValue(Optional<StringBuilder> obs) {
		return constValue;
	}

}