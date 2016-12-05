package logic;

import java.util.function.Supplier;

import logic.condition.Condition;
import logiс.operators.And;
import logiс.operators.Or;

public abstract class Combinator {

	public static And and = new And();

	public static Or or = new Or();

	public Supplier<Boolean> prorogue(Supplier<Boolean> supplier) {
		return supplier;
	}

	protected abstract String operatorName();

	private boolean wrap(Combinator combinator, StringBuilder log, Condition left, Condition right) {
		log.append(operatorName());
		log.append("(");
		final boolean v = eval(log, left, right);
		log.append(")");
		return v;
	}

	protected abstract boolean eval(StringBuilder log, Condition left, Condition right);

	public boolean combine(StringBuilder log, Condition left, Condition right) {
		return wrap(this, log, left, right);
	}

}