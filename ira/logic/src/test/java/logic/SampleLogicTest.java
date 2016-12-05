package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SampleLogicTest extends SampleLogicTestBase implements LogicOperatorUtils {

	@Test
	public void test$isPageTest$false() {
		assertEquals("false|isPage:false", withLog(isPage));
	}

	@Test
	public void test$isPageTest$true() {
		isPage.setValue(true);
		assertEquals("true|isPage:true", withLog(isPage));
	}

	@Test
	public void tetst$001() {
		assertEquals("false|printSetter[and(isPage:false)]", withLog(printSetter));
	}

	@Test
	public void test$002() {
		isPage.setValue(true);
		isSetter.setValue(true);
		assertEquals("true|printSetter[and(isPage:true,isSetter:true)]", withLog(printSetter));
	}

	@Test
	public void test$simpleEvidence$true() {
		isPage.setValue(true);
		assertEquals("true|isPage:true", withLog(isPage));
	}

	@Test
	public void test$simpleEvidence$false() {
		isPage.setValue(false);
		assertEquals("false|isPage:false", withLog(isPage));
	}

	@Test
	public void test$combined$simplest$checkLazy() {
		assertEquals("false|printSetter[and(isPage:false)]", withLog(printSetter));
	}

	@Test
	public void test$combined$simplest$andEvaluatedToFirstNotFalseValue() {
		isPage.setValue(true);
		isSetter.setValue(true);
		assertEquals("true|printSetter[and(isPage:true,isSetter:true)]", withLog(printSetter));
	}

	@Test
	public void test$combined$or() {
		isSetter.setValue(true);
		isGetter.setValue(true);
		assertEquals("true|isField[or(isSetter:true)]", withLog(isField));
	}

	@Test
	public void test$combined$or$deepth() {
		isSetter.setValue(false);
		isGetter.setValue(true);
		assertEquals("true|isField[or(isSetter:false,isGetter:true)]", withLog(isField));
	}
}
