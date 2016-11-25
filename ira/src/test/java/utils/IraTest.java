package utils;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * TODO Добавить проверку, что у теста есть необходимые аннотации с описанием
 * его ответственности
 **/
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

	protected void print(Object... items) {
		boolean first = true;
		for (final Object object : items) {
			if (!first) {
				System.out.print(" ");

			}
			System.out.print(object.toString());
			first = false;
		}
		System.out.println();
	}

}
