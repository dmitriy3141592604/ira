package logic;

import org.junit.Before;

public abstract class SampleLogicTestBase implements LogicOperatorUtils {

	protected SimpleCondition isPage;
	protected SimpleCondition isSetter;
	protected SimpleCondition isGetter;
	private SimpleCondition isSubmit;
	protected StringBuilder log;
	protected CombinedCondition printSetter;
	protected CombinedCondition isField;

	@Before
	public void setUpSampleLogicTestBase() {
		isPage = new SimpleCondition("isPage");
		isSetter = new SimpleCondition("isSetter");
		isGetter = new SimpleCondition("isGetter");
		isSubmit = new SimpleCondition("isSubmit");

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
