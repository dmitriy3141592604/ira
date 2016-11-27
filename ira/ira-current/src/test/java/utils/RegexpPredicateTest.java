package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RegexpPredicateTest {

	protected Boolean isMatch(String pattern, String string) {
		return new RegexpPredicate(pattern).test(string);
	}

	@Test
	public void test$match() {
		assertEquals(Boolean.TRUE, isMatch("a.c", "abc"));
	}

	@Test
	public void test$noMatch() {
		assertEquals(Boolean.FALSE, isMatch("a.d", "abc"));
	}

}
