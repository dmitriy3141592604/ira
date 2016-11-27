package utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class ActorTestHelper implements Actor {

}

public class ActorTest extends IraTest {

	@Test
	public void test$isPresent() {
		assertEquals(true, Actor.class.isAssignableFrom(ActorTestHelper.class));
	}

}
