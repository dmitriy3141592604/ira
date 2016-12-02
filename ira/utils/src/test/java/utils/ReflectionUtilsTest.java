package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.ReflectionUtils.isAnnotationPresent;

import org.junit.Test;

public class ReflectionUtilsTest {

	private static final Class<?> NEGATIVE_HELPER_CLASS = ReflectionUtilsTestNegativeHelper.class;

	private static final Class<?> MUDDLE_INTERFACE = ReflectionUtilsTestHelperInterfaceMuddle.class;

	private static final Class<?> POSITIVE_HELPER_INTERFACE = ReflectionUtilsTestHelperInterface.class;

	private static final Class<?> POSITIVE_HELPER_CLASS = ReflectionUtilsTestPositiveHelper.class;

	interface ReflectionUtilsTestHelperInterface {

	}

	interface ReflectionUtilsTestHelperInterfaceMuddle {

	}

	private static class ReflectionUtilsTestPositiveHelper implements ReflectionUtilsTestHelperInterface {

	}

	private static class ReflectionUtilsTestNegativeHelper {

	}

	@Test
	public void test$forEmma() {
		assertNotNull(new ReflectionUtils());
	}

	@Test
	public void test$positive() {
		assertEquals(true, isAnnotationPresent(POSITIVE_HELPER_CLASS, POSITIVE_HELPER_INTERFACE));
	}

	@Test
	public void test$positive$muddle() {
		assertEquals(false, isAnnotationPresent(POSITIVE_HELPER_CLASS, MUDDLE_INTERFACE));
	}

	@Test
	public void test$negative() {
		assertEquals(false, isAnnotationPresent(NEGATIVE_HELPER_CLASS, POSITIVE_HELPER_INTERFACE));
	}

}
