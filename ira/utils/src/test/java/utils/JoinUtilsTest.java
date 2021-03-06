package utils;

import static org.junit.Assert.assertEquals;
import static utils.JoinUtils.join;

import org.junit.Test;

public class JoinUtilsTest extends JoinUtilsTestBase {

	@Test
	public void test$joinEmptyCollection() {
		assertEquals("", join("", items));
	}

	@Test
	public void test$joinOneElement() {
		final String rs = items.remember(randomString());
		assertEquals(rs, join("", items));
	}

	@Test
	public void test$twoValuesSeparated() {
		final String rs1 = items.remember(randomString());
		final String rs2 = items.remember(randomString());
		final String rs3 = items.remember(randomString());
		assertEquals(rs1 + "|" + rs2 + "|" + rs3, join("|", items));
	}

}
