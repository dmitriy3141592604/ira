package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ReflectionUtilsTest {

	interface ReflectionUtilsTestHelperInterface {

	}

	interface ReflectionUtilsTestHelperInterfaceMuddle {

	}

	private static class ReflectionUtilsTestPositiveHelper implements ReflectionUtilsTestHelperInterface {

	}

	private static class ReflectionUtilsTestNegativeHelper {

	}

	@Test // For Eмма
	public void test$instantiate() {
		assertNotNull(new ReflectionUtils());
	}

	@Test
	public void test$positive() {
		assertEquals(true, ReflectionUtils.isAnnotationPresent(ReflectionUtilsTestPositiveHelper.class, ReflectionUtilsTestHelperInterface.class));
	}

	@Test
	public void test$positive$muddle() {
		assertEquals(false,
				ReflectionUtils.isAnnotationPresent(ReflectionUtilsTestPositiveHelper.class, ReflectionUtilsTestHelperInterfaceMuddle.class));
	}

	@Test
	public void test$negative() {
		assertEquals(false, ReflectionUtils.isAnnotationPresent(ReflectionUtilsTestNegativeHelper.class, ReflectionUtilsTestHelperInterface.class));
	}

}
