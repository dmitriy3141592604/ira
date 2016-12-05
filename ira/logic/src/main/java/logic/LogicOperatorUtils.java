package logic;

public interface LogicOperatorUtils {

	default CombinedCondition and(String name, Condition... conditions) {
		return new CombinedCondition(name, new LogicOperatorAnd(), conditions);
	};

	default CombinedCondition or(String name, Condition... conditions) {
		return new CombinedCondition(name, new LogicOperatorOr(), conditions);
	};
}
