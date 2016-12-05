package logic;

public interface LogicOperatorUtils {

	default ConditionCombined and(String name, Condition... conditions) {
		return new ConditionCombined(name, new LogicOperatorAnd(), conditions);
	};

	default ConditionCombined or(String name, Condition... conditions) {
		return new ConditionCombined(name, new LogicOperatorOr(), conditions);
	};

	default ConditionCombined not(String name, Condition condition) {
		return new ConditionCombined(name, new LogicOperatorNot(), condition);
	};
}
