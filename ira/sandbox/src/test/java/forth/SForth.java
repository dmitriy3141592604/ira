package forth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SForth {

	private final FStack stack = new FStack();

	// Контракт: Значения нал в качестве ключа или значения недопусимы
	private final Map<String, List<FToken>> memory;

	public SForth(Map<String, List<FToken>> memory) {
		this.memory = memory;
	}

	private Function<Iterator<? extends FToken>, SForth> tokenProcessingStrategy = this::applayTokens;

	public SForth applay(Iterator<? extends FToken> token) {
		return tokenProcessingStrategy.apply(token);
	}

	public SForth applayTokens(Iterator<? extends FToken> token) {
		while (token.hasNext()) {
			token.next().applay(stack);
		}
		return this;
	}

	public SForth saveTokens(Iterator<? extends FToken> token) {
		while (token.hasNext()) {
			final String stringValue = stack.top().stringValue();
			memory.get(stringValue).add(token.next());
		}
		return this;
	}

	// sForth.applay(new FLexeme("!"));
	public SForth applay(FLexeme fLexeme) {
		final String value = fLexeme.getValue();

		if ("[".equals(value)) {
			tokenProcessingStrategy = this::saveTokens;
			memory.put(stack.top().stringValue(), new ArrayList<>());
			return this;
		}
		if ("]".equals(value)) {
			tokenProcessingStrategy = this::applayTokens;
			return this;
		}

		{
			/** Заметим, что fLexeme всегда > 0 **/
			if ('"' == value.charAt(0)) {
				final String stringValue = value.substring(1, value.length() - 1);
				return applay(Arrays.asList(new FString(stringValue)).iterator());
			}
		}
		{
			boolean isAllCharsIsDigits = true;
			for (int i = 0; i < value.length(); ++i) {
				isAllCharsIsDigits &= Character.isDigit(value.charAt(i));
			}
			if (isAllCharsIsDigits) {
				return applay(Arrays.asList(new FInteger(Integer.valueOf(value))).iterator());
			}
		}
		{
			final List<FToken> operatorList = memory.get(value);
			if (operatorList != null) {
				return applay(operatorList.iterator());
			}
		}
		throw new IllegalArgumentException("Not allowed token: " + value);
	}

}