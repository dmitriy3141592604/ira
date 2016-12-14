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

		// TODO Основной тест не проверяет, что обрабатываются все условия
		// final boolean eval = operator.eval(oLog, conditions[0]); отлично
		// проходит
		final boolean eval = operator.eval(oLog, conditions);

		oLog.ifPresent((log) -> {
			log.append("]");
		});
		return eval;
	}

}
