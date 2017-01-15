package testutils;

import org.junit.Before;

public abstract class MutableAssertTestBase {

	protected String value;

	@Before
	public final void setUpMutableAssertTestBase() {
		value = null;
	}
}
