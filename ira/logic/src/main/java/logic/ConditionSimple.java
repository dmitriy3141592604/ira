package logic;

import java.util.Optional;

public class ConditionSimple extends ConditionBase {

	private boolean value;

	public ConditionSimple(String name) {
		this(name, false);
	}

	public ConditionSimple(String name, boolean value) {
		super(name);
		this.value = value;
	}

	@Override
	public boolean getValue(Optional<StringBuilder> oLog) {
		oLog.ifPresent(log -> log.append(name).append(":").append(value));
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public void setOn() {
		setValue(true);
	}

	public void setOff() {
		setValue(false);
	}
}
