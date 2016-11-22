package org.i2g.ira;

import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import utils.IraTest;
import utils.Value;

@RunWith(MockitoJUnitRunner.class)
public class ContextClassTest extends IraTest {

	@Test
	public void test$setApplicationContext() {
		final ApplicationContext mock = Mockito.mock(ApplicationContext.class);
		final Value<ApplicationContext> value = new Value<ApplicationContext>();

		new ContextClass() {

			@Override
			public void setApplicationContext(ApplicationContext applicationContext) {
				super.setApplicationContext(applicationContext);
				value.setValue(this.applicationContext);
			}

		}.setApplicationContext(mock);
		assertSame(mock, value.getValue());
	}

}
