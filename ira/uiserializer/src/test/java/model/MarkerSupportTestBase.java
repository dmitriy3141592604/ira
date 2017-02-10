package model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import testutils.RandomizedTest;

public abstract class MarkerSupportTestBase implements RandomizedTest {

	public static class AlwaysEqual {

		@Override
		public boolean equals(Object obj) {
			return true;
		}

	}

	public static class AlwaysNotEquals {
		@Override
		public boolean equals(Object obj) {
			return false;
		}

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected MarkerSupport mt;

	protected String marker;

	protected String otherMarker;

	protected String value;

	protected String defaultValue;

	@Before
	public final void setUpMetaSupportTestBase() {
		mt = new MarkerSupport();
		marker = randomString();
		otherMarker = randomString();
		value = randomString();
		defaultValue = randomString();
	}

}