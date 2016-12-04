package logic;

public class And extends Combinator {

	protected boolean eval(StringBuilder log, Condition left, Condition right) {
		return left.getValue(log) && prorogue(() -> right.getValue(log.append(","))).get();
	}

	@Override
	public String operatorName() {
		return "and";
	}

}