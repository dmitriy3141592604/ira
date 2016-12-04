package logic.combinators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import logic.Combinator;
import logic.condition.BinaryCondition;
import logic.condition.Condition;

public class CombinedCondition extends BinaryCondition {

	private final List<Condition> source;

	private final Combinator combinator;

	public CombinedCondition(Combinator combinator, String name, Condition... conditions) {
		super(name);
		this.combinator = combinator;
		this.source = Arrays.asList(conditions);
	}

	@Override
	public Condition and(String name, Condition condition) {
		return combinate(Combinator.and, name, condition);
	}

	@Override
	public Condition or(String name, Condition condition) {
		return combinate(Combinator.or, name, condition);
	}

	@Override
	public boolean getValue(StringBuilder log) {
		boolean retVal = true;
		final Iterator<Condition> iterator = source.iterator();
		final Condition first = iterator.next();
		while (iterator.hasNext()) {
			retVal = combinator.eval(combinator, log, first, iterator.next());
		}
		return retVal;
	}

}