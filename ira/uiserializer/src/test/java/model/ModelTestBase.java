package model;

import org.junit.Before;

import testutils.RandomizedTest;

public abstract class ModelTestBase implements RandomizedTest {

	protected String nodeName;

	protected String edgeName;

	@Before
	public final void setUpModelTestBase() {
		nodeName = randomString();
		edgeName = randomString();
	}

}
