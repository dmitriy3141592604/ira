package logic;

public class CombinedCondition extends ConditionBase {

	private final LogicOperator operator;

	private final Condition[] conditions;

	public CombinedCondition(String name, LogicOperator operator, Condition... conditions) {
		super(name);
		this.operator = operator;
		this.conditions = conditions;
	}

	@Override
	public boolean getValue(StringBuilder log) {
		log.append(name);
		log.append("[");
		final boolean eval = operator.eval(log, conditions);
		log.append("]");
		return eval;
	}

}
