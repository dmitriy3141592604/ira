package logic;

import java.util.Optional;

import utils.Value;

public class LogicOperatorOr implements LogicOperator {

	@Override
	public boolean eval(Optional<StringBuilder> oLog, Condition... conditions) {
		if (conditions == null || conditions.length == 0) {
			throw new IllegalArgumentException("Пустые списки условий недопусимы");
		}
		try {
			oLog.ifPresent(log -> {
				log.append(getName());
				log.append("(");
			});

			final Value<String> comma = Value.newValue("");
			boolean retVal = false;
			for (final Condition condition : conditions) {
				oLog.ifPresent(log -> {
					log.append(comma.getValue());

				});
				comma.setValue(",");
				retVal |= condition.getValue(oLog);
				if (retVal) {
					return retVal;
				}
			}
			return retVal;
		} finally {
			oLog.ifPresent(log -> {
				log.append(")");
			});
		}
	}

	@Override
	public String getName() {
		return "or";
	}

}
