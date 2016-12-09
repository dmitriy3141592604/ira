package logic;

import java.util.Optional;

import utils.Value;

public class LogicOperatorAnd implements LogicOperator {

	@Override
	public boolean eval(Optional<StringBuilder> oLog, Condition... conditions) {

		if (conditions == null || conditions.length == 0) {
			throw new IllegalArgumentException("Пустые списки условий недопустиы");
		}
		oLog.ifPresent(log -> {
			log.append(getName());
			log.append("(");
		});
		boolean returnValue = true;
		final Value<String> comma = Value.<String>newValue("");
		try {
			for (final Condition condition : conditions) {
				oLog.ifPresent(log -> {
					log.append(comma.getValue());
				});
				comma.setValue(",");
				returnValue &= condition.getValue(oLog);
				if (!returnValue) {
					return returnValue;
				}
			}
		} finally {
			oLog.ifPresent(log -> {
				log.append(")");
			});
		}
		return returnValue;
	}

	@Override
	public String getName() {
		return "and";
	}

}
