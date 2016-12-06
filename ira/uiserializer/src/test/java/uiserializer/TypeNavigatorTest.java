package uiserializer;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class TypeNavigatorTest {

	private TypeNavigator navigator;

	@Before
	public final void setUpTypeNavigatorTest() {
		navigator = new TypeNavigator(MethodModelTestHelper.class);
	}

	@Test
	public void test$models() {
		final Collection<MethodModel> methods = navigator.models();
		assertEquals("1:getCount", "" + methods.size() + ":" + methods.iterator().next().getName());
	}

	@Test
	public void test$simpleName() {
		assertEquals("MethodModelTestHelper", navigator.simpleName());
	}

}
