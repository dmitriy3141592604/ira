package forth;

import static utils.Quietly.quietly;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import utils.io.OnFileReader;

public class SForth {

	public static void main(String... args) throws Exception {
		final HashMap<String, List<FToken>> readMemory = SForth.readMemory("functionList.fthlib");
		final SForth sForth = new SForth(readMemory);

		final LineIterator lineIterator = IOUtils.lineIterator(System.in, "utf-8");
		while (lineIterator.hasNext()) {
			final String readLine = lineIterator.nextLine();
			if (readLine.contains("\"")) {
				sForth.applay(new FLexeme(readLine));
			} else {
				for (final String token : readLine.split("\\s+")) {
					sForth.applay(new FLexeme(token));
				}
			}
		}

	}

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

	public static HashMap<String, List<FToken>> readMemory(String memoryFileName) {
		final HashMap<String, List<FToken>> memory = new HashMap<>();
		{
			new OnFileReader(new File(memoryFileName)).accept(br -> {
				br.lines().forEach(originalString -> {
					if (originalString.matches("^\\s*[#]")) {
						return;
					}
					final String string = originalString.replaceAll("^.*[.]..", "");
					final String functionName = Character.toLowerCase(string.charAt(0)) + string.substring(1);
					quietly(() -> {
						final Class<?> operationClassName = Class.forName(originalString);
						final Object operationObject = operationClassName.newInstance();
						final FToken cast = FToken.class.cast(operationObject);
						final List<FToken> oldList = memory.get(functionName);
						if (oldList == null) {
							final ArrayList<FToken> value = new ArrayList<>();
							value.add(cast);
							memory.put(functionName, value);
						} else {
							oldList.clear();
							oldList.add(cast);
						}
					});
				});
			});
		}
		return memory;
	}

}