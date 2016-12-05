package logic;

public class LogicOperatorOr implements LogicOperator {

	@Override
	public boolean eval(StringBuilder log, Condition... conditions) {
		if (conditions == null || conditions.length == 0) {
			throw new IllegalArgumentException("Пустые списки условий недопусимы");
		}
		try {
			log.append(getName());
			log.append("(");
			String comma = "";
			boolean retVal = false;
			for (final Condition condition : conditions) {
				log.append(comma);
				comma = ",";
				retVal |= condition.getValue(log);
				if (retVal) {
					return retVal;
				}
			}
			return retVal;
		} finally {
			log.append(")");
		}
	}

	@Override
	public String getName() {
		return "or";
	}

}
