package logic;

import java.util.Optional;

public class LogicOperatorNot implements LogicOperator {

	@Override
	public boolean eval(Optional<StringBuilder> oLog, Condition... conditions) {

		if (conditions.length != 1) {
			throw new LogicOperatorNotException();
		}

		oLog.ifPresent(log -> log.append(getName()).append("("));
		final boolean result = !conditions[0].getValue(oLog);
		oLog.ifPresent(log -> log.append(")"));
		return result;
	}

	@Override
	public String getName() {
		return "not";
	}

}
