package org.i2g.ira.uibuilder;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import test.uibuilder.DefaultMethodTransformer;
import testutils.RandomizedTest;

public abstract class DefaultMethodTransformerTestBase implements RandomizedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private DefaultMethodTransformer methodTransformer;

	protected String rs;

	protected final Runnable runnable = () -> {
	};

	@Before
	public final void setUpDefaultMethodTransformerTestBase() {
		methodTransformer = new DefaultMethodTransformer();
		rs = randomString();
	}

	protected Tag transform(final Method method, Object... args) {
		return methodTransformer.applay(method, args);
	}

	protected Tag transform(final Method method) {
		return methodTransformer.applay(method, null);
	}

}
