package logic;

public class SimpleCondition extends ConditionBase {

	private boolean value;

	public SimpleCondition(String name) {
		this(name, false);
	}

	public SimpleCondition(String name, boolean value) {
		super(name);
		this.value = value;
	}

	@Override
	public boolean getValue(StringBuilder log) {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
}
