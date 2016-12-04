package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import logic.combinators.SimpleCondition;
import logic.condition.Condition;

public class SampleLogicTest {

	private Condition isPage;

	private Condition isSetter;

	private Condition isGetter;

	private Condition isSubmit;

	private StringBuilder log;

	private Condition printSetter;

	private Condition isField;

	@Before
	public void setUpSampleLogicTestBase() {
		isPage = new SimpleCondition("isPage");
		isSetter = new SimpleCondition("isSetter");
		isGetter = new SimpleCondition("isGetter");
		isSubmit = new SimpleCondition("isSubmit");

		printSetter = isPage.and("printSetter", isSetter);

		isField = isSetter.or("isField", isGetter);

		log = new StringBuilder();
	}

	@Test
	public void test$isPageTest$false() {
		assertEquals("false isPage:false", withLog(isPage));
	}

	@Test
	public void test$isPageTest$true() {
		isPage.setValue(true);
		assertEquals("true isPage:true", withLog(isPage));
	}

	private String withLog(Condition condition) {
		return "" + condition.getValue(log) + " " + log.toString();
	}

	@Test
	public void tetst$001() {
		final Condition printSetter = isPage.and("printSetter", isSetter);
		assertEquals(false, printSetter.getValue(new StringBuilder()));
	}

	@Test
	public void test$002() {
		isPage.setValue(true);
		isSetter.setValue(true);

		assertEquals(true, printSetter.getValue(new StringBuilder()));
	}

	@Test
	public void test$simpleEvidence$true() {
		isPage.setValue(true);
		assertEquals("true isPage:true", withLog(isPage));
	}

	@Test
	public void test$simpleEvidence$false() {
		isPage.setValue(false);
		withLog(isPage);
		assertEquals("isPage:false", log.toString());
	}

	@Test
	public void test$combined$simplest$checkLazy() {
		printSetter.getValue(log);
		assertEquals("and(isPage:false)", log.toString());
	}

	@Test
	public void test$combined$simplest$andEvaluatedToFirstNotFalseValue() {
		isPage.setValue(true);
		isSetter.setValue(true);
		printSetter.getValue(log);
		assertEquals("and(isPage:true,isSetter:true)", log.toString());
	}

	@Test
	public void test$combined$or() {
		isSetter.setValue(true);
		isGetter.setValue(true);
		isField.getValue(log);
		assertEquals("or(isSetter:true)", log.toString());
	}

	@Test
	public void test$combined$or$deepth() {
		isSetter.setValue(false);
		isGetter.setValue(true);
		isField.getValue(log);
		assertEquals("or(isSetter:false,isGetter:true)", log.toString());
	}
}
