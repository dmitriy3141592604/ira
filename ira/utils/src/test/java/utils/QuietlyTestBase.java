package utils;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;

public abstract class QuietlyTestBase implements RandomizedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected String rs = randomString();

}