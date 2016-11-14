package utils;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class IraTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected void except(Class<? extends Exception> type) {
		exception.expect(type);
	}

}
