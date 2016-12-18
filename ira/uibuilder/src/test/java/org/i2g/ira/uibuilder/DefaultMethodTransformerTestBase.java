package org.i2g.ira.uibuilder;

import static utils.Safer.safe;

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

	protected final Method toStringMethod = safe(() -> String.class.getMethod("toString"));

	@Before
	public final void setUpDefaultMethodTransformerTestBase() {
		methodTransformer = new DefaultMethodTransformer();
		rs = randomString();
	}

	protected Tag transform(Method method, Object... args) {
		return methodTransformer.applay(method, args);
	}

	protected Tag transform(Method method) {
		return methodTransformer.applay(method, null);
	}

	protected TextElement asTextElement(Tag tag) {
		return (TextElement) tag;
	}

	protected Element asElement(Tag tag) {
		return (Element) tag;
	}

}
