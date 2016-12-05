package logic;

public interface LogicOperator {

	default CombinedCondition and(String name, Condition... conditions) {
		throw new RuntimeException("Not implemented");
	};

	default CombinedCondition or(String name, Condition... conditions) {
		throw new RuntimeException("Not implemented");
	};
}
