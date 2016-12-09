package logic;

import java.util.Optional;

public class ConditionCombined extends ConditionBase {

	private final LogicOperator operator;

	private final Condition[] conditions;

	public ConditionCombined(String name, LogicOperator operator, Condition... conditions) {
		super(name);
		this.operator = operator;
		this.conditions = conditions;
	}

	@Override
	public boolean getValue(Optional<StringBuilder> oLog) {
		oLog.ifPresent((log) -> {
			log.append(name);
			log.append("[");
		});

		final boolean eval = operator.eval(oLog, conditions);

		oLog.ifPresent((log) -> {
			log.append("]");
		});
		return eval;
	}

}
