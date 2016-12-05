package logic;

import org.junit.Before;

public abstract class SampleLogicTestBase implements LogicOperatorUtils {

	protected ConditionSimple isPage;
	protected ConditionSimple isSetter;
	protected ConditionSimple isGetter;
	protected StringBuilder log;
	protected ConditionCombined printSetter;
	protected ConditionCombined isField;

	@Before
	public void setUpSampleLogicTestBase() {
		isPage = new ConditionSimple("isPage");
		isSetter = new ConditionSimple("isSetter");
		isGetter = new ConditionSimple("isGetter");

		printSetter = and("printSetter", isPage, isSetter);

		isField = or("isField", isSetter, isGetter);

		log = new StringBuilder();

	}

	protected String withLog(Condition condition) {
		final StringBuilder resultString = new StringBuilder();
		resultString.append(condition.getValue(log));
		resultString.append("|");
		resultString.append(log.toString());
		return resultString.toString();
	}

}
