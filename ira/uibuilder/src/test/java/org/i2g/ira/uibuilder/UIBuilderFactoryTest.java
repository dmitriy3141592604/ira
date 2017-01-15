package org.i2g.ira.uibuilder;

import static org.i2g.ira.uibuilder.UIBuilderFactory.newUIBuilderFactory;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Test;

public class UIBuilderFactoryTest extends UIBuilderFactoryTestBase {

	@Test
	public void test$defaultCreation() {
		final UIBuilderFactory factory = newUIBuilderFactory(new Element(rs), new Transformer<Method, Tag>() {

			@Override
			public Tag applay(Method from, Object[] args) {
				throw new UnsupportedOperationException();
			}
		});

		assertNotNull(factory);

	}

	@Test(expected = IllegalArgumentException.class)
	public void test$nullRootElementNotAllowed() {
		newUIBuilderFactory(null, new Transformer<Method, Tag>() {

			@Override
			public Tag applay(Method from, Object[] args) {
				throw new UnsupportedOperationException();
			}
		});
	}

	@Test(expected = IllegalArgumentException.class)
	public void test$nullTransformerNotAllowed() {
		newUIBuilderFactory(new Element(rs), null);
	}

}
