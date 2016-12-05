package logic;

public class LogicOperatorAnd implements LogicOperator {

	@Override
	public boolean eval(StringBuilder log, Condition... conditions) {

		if (conditions == null || conditions.length == 0) {
			throw new IllegalArgumentException("Пустые списки условий недопустиы");
		}

		log.append(getName());
		log.append("(");
		boolean returnValue = true;
		String comma = "";
		try {
			for (final Condition condition : conditions) {
				log.append(comma);
				comma = ",";
				returnValue &= condition.getValue(log);
				if (!returnValue) {
					return returnValue;
				}
			}
		} finally {
			log.append(")");
		}
		return returnValue;
	}

	@Override
	public String getName() {
		return "and";
	}

}
