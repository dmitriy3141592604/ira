package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;

public class ReflectionUtilsTest {

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
	@interface ReflectionUtilsTestHelperInterface {

	}

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
	@interface ReflectionUtilsTestHelperInterfaceMuddle {

	}

	@ReflectionUtilsTestHelperInterface
	private static class ReflectionUtilsTestPositiveHelper {

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
