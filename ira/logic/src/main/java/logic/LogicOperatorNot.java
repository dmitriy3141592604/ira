package logic;

import java.util.Optional;

public class LogicOperatorNot implements LogicOperator {

	@Override
	//@NotTested
	// TODO Test me
	public boolean eval(Optional<StringBuilder> oLog, Condition... conditions) {
		if (conditions == null || conditions.length != 1) {
			throw new IllegalArgumentException("expected exactly one condition");
		}
		return !conditions[0].getValue();
	}

	@Override
	public String getName() {
		return "not";
	}

}
