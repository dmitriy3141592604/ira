package utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

@Actor
class ActorTestHelper {

}

public class ActorTest extends IraTest {

	@Test
	public void test$isPresent() {
		assertNotNull(ActorTestHelper.class.getAnnotation(Actor.class));
	}

}
