package structure;

import org.junit.Before;

public abstract class MetaSupportTestBase {

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

	protected MetaSupport mt;

	@Before
	public final void setUpMetaSupportTestBase() {
		mt = new MetaSupport();
	}

}