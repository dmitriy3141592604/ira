package utils;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

/**
 * Author: timofeevdv Date: 06.02.2017
 */
public class RPNTest {
	@Test
	public void testSimpleOperation() {
		assertEquals("12+", RPN.make("1+2"));
	}

	@Test
	public void testTwoOperations() {
		assertEquals("12+3+", RPN.make("1+2+3"));
	}

	@Test
	public void testOperationPriority() {
		assertEquals("234*+", RPN.make("2+3*4"));
	}

	@Test
	public void testExpressionWithBrackets() {
		assertEquals("23+4*5/", RPN.make("(2+3)*4/5"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectBrackets() {
		RPN.make("2+3)*4");
	}

	@Test
	public void testAddition() {
		assertEquals(3.0, RPN.calculate("12+"));
	}

	@Test
	public void testSubtraction() {
		assertEquals(3.0, RPN.calculate("52-"));
	}

	@Test
	public void testMultiplication() {
		assertEquals(10.0, RPN.calculate("52*"));
	}

	@Test
	public void testDivision() {
		assertEquals(2.5, RPN.calculate("52/"));
	}

	@Test
	public void testComplexExpresion() {
		assertEquals(4.0, RPN.calculate("23+4*5/"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateInvalidExpression() {
		RPN.calculate("2&2");
	}
}
