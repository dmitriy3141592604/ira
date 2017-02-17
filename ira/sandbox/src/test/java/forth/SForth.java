package forth;

import java.util.Map;

public class SForth {

	private final FStack stack = new FStack();

	// Контракт: Значения нал в качестве ключа или значения недопусимы
	private final Map<String, FToken> memory;

	public SForth(Map<String, FToken> memory) {
		this.memory = memory;
	}

	public SForth applay(FToken token) {
		token.applay(stack);
		return this;
	}

	// sForth.applay(new FLexeme("!"));
	public SForth applay(FLexeme fLexeme) {
		final String value = fLexeme.getValue();
		{
			/** Заметим, что fLexeme всегда > 0 **/
			if ('"' == value.charAt(0)) {
				final String stringValue = value.substring(1, value.length() - 1);
				return applay(new FString(stringValue));
			}
		}
		{
			boolean isAllCharsIsDigits = true;
			for (int i = 0; i < value.length(); ++i) {
				isAllCharsIsDigits &= Character.isDigit(value.charAt(i));
			}
			if (isAllCharsIsDigits) {
				return applay(new FInteger(Integer.valueOf(value)));
			}
		}
		{
			final FToken operator = memory.get(value);
			if (operator != null) {
				return applay(operator);
			}
		}
		throw new IllegalArgumentException("Not allowed token: " + value);
	}

}