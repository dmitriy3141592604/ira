package utils;

import org.junit.Before;

public abstract class ContractTestBase extends IraTest {

	private Range v;

	public static class Range {

		public String begin;

		public String end;
	}

	@Before
	public final void setUpContractTestBase() {

	}

	protected <T> void validate(T v) {
		createValidator().validate(v);
	}

	protected abstract <T> Contract<T> createValidator();

}
