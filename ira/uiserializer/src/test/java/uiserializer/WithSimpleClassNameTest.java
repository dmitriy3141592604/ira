package uiserializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import utils.Behavior;

public class WithSimpleClassNameTest {

	public static class SimpleClassNamedTestHelper implements WithSimpleClassName {

	}

	@Test
	@Behavior("#name возвращает простое имя класса")
	public void test$nameReturnSimpleName() {
		assertEquals("SimpleClassNamedTestHelper", new SimpleClassNamedTestHelper().name());
	}

}
