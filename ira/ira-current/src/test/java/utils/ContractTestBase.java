package utils;

import org.junit.Before;

public abstract class ContractTestBase<T> extends IraTest {

	// private Range v;

	public static class Range {

		public String begin;

		public String end;
	}

	@Before
	public final void setUpContractTestBase() {

	}

	protected void validate(T v) {
		createValidator().validate(v);
	}

	protected abstract Contract<T> createValidator();

}
