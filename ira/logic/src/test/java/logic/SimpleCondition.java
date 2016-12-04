package logic;

import static logic.Const.wrappedConst;

public class SimpleCondition extends BinaryCondition {

	private boolean value;

	public SimpleCondition(String name) {
		super(name);
	}

	@Override
	public boolean getValue(StringBuilder log) {
		return wrappedConst(value, () -> log.append(getName()).append(":").append(value));
	}

	@Override
	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public Condition and(String name, Condition condition) {
		return combinate(Combinator.and, name, condition);
	}

	@Override
	public Condition or(String name, Condition condition) {
		return combinate(Combinator.or, name, condition);
	}

}