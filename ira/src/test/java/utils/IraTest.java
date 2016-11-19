package utils;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class IraTest {

	private StringBuilder testLog;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected void except(Class<? extends Exception> type) {
		exception.expect(type);
	}

	protected void log(Object message) {
		testLog.append(message.toString());
	}

	protected String resultLog() {
		return testLog.toString();
	}

}
