package utils;

import static java.util.regex.Pattern.compile;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class RegexpPredicate implements Predicate<String> {

	private final Pattern pattern;

	public RegexpPredicate(String pattern) {
		this.pattern = compile(pattern);
	}

	@Override
	public boolean test(String t) {
		return pattern.matcher(t).matches();
	}
}
