package org.i2g.ira.uibuilder;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class UIBuilderFactoryTestBase implements RandomizedTest {

	protected String rs;

	@Before
	public final void setUpUIBuilderFactoryTestBase() {
		rs = randomString();
	}
}
