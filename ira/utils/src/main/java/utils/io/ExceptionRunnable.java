package utils.io;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс для операций, без входных параметров, результата и с контролируемым типом исключения")
public interface ExceptionRunnable {

	void run() throws Exception;

	default void quietly() {
		try {
			run();
		} catch (final RuntimeException exception) {
			throw exception;
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}