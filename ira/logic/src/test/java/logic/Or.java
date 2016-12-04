package logic;

public class Or extends Combinator {

	@Override
	public String operatorName() {
		return "or";
	}

	protected boolean eval(StringBuilder log, Condition left, Condition right) {
		return left.getValue(log) || prorogue(() -> right.getValue(log.append(","))).get();
	}

}