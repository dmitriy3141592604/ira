package utils;

public abstract class ContractTestBase extends IraTest {

	public final void setUpContractTestBase() {

	}

	protected <T> void validate(T v2) {
		createValidator().validate(v2);
	}

	protected abstract <T> Contract<T> createValidator();

}
