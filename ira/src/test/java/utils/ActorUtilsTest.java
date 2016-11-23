package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.ActorUtils.hasActorAnnotation;

import org.junit.Test;

public class ActorUtilsTest {

	@Actor
	/** Анотация {@see Actor} присутствует **/
	public static class ActorUtilsTestHelperPositive {

	}

	/** Анотация {@see Actor} отсутсвует **/
	public static class ActorUtilsTestHelperNegative {

	}

	@Test
	public void test$Mositive() {
		assertEquals(true, hasActorAnnotation(ActorUtilsTestHelperPositive.class));
	}

	@Test
	public void test$Negative() {
		assertEquals(false, hasActorAnnotation(ActorUtilsTestHelperNegative.class));
	}

	@Test
	public void test$defaultConstructorForIncreasetCoverate() {
		final ActorUtils actorUtils = new ActorUtils() {

		};
		assertNotNull(actorUtils);
	}

}
