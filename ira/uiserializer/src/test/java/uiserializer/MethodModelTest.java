package uiserializer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MethodModelTest {

	private MethodModel model;

	@Before
	public final void setUpMethodModelTest() {
		this.model = new MethodModel(() -> MethodModelTestHelper.class.getMethod("getCount", String.class));
	}

	@Test
	public void test$getName() {
		assertEquals("getCount", model.getName());
	}

	@Test
	public void test$getReturnType() {
		assertEquals(Integer.class, model.getReturnType());
	}

	@Test
	public void test$from() {
		assertEquals("methodHelperValue", model.from(MethodHelper.class).value());
	}

	@Test
	public void test$returnNavigator() {
		assertEquals("Integer", model.getReturnTypeNavigator().simpleName());
	}

}
