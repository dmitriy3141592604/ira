package forth;

import utils.Responsibility;

@Responsibility("Отвечает за хранение лексемы разобранной из входного потока")
public class FLexeme {
	/**
	 * <code>
	 *
	 * private int line;
	 *
	 * private int column;
	 *
	 * private String fileName;
	 */

	private final String value;

	public FLexeme(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
