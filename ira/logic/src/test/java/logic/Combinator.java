package logic;

import java.util.function.Supplier;

import logic.condition.Condition;
import logiс.operators.And;
import logiс.operators.Or;

public abstract class Combinator {

	public static Combinator and = new And();

	public static Combinator or = new Or();

	public Supplier<Boolean> prorogue(Supplier<Boolean> supplier) {
		return supplier;
	}

	public boolean eval(Combinator combinator, StringBuilder log, Condition left, Condition right) {
		return combinator.combine(combinator, log, left, right);
	}

	public abstract String operatorName();

	protected boolean wrap(Combinator combinator, StringBuilder log, Condition left, Condition right) {
		log.append(operatorName());
		log.append("(");
		final boolean v = eval(combinator, log, left, right);
		log.append(")");
		return v;
	}

	public boolean combine(Combinator combinator, StringBuilder log, Condition left, Condition right) {
		return wrap(this, log, left, right);
	}

}