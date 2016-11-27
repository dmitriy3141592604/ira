package utils;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MethodProcessorTest extends IraTest {

	private MethodProcessor processor;

	private HashMap<String, Object> valuesHolder;

	@Before
	public void setUpMethodProcessorTestBase() {
		valuesHolder = new HashMap<String, Object>();
		processor = new MethodProcessor(valuesHolder);
	}

	@Test
	public void test$() throws Throwable {
		except(IllegalArgumentException.class);
		processor.invoke(new Object(), String.class.getMethod("toString"), new Object[] {});
	}
}
