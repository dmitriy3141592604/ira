package logic;

public class CombinedCondition extends ConditionBase {

	public CombinedCondition(String name, Condition... conditions) {
		super(name);
	}

	@Override
	public boolean getValue(StringBuilder log) {
		throw new RuntimeException("Not implemented");
	}

}
