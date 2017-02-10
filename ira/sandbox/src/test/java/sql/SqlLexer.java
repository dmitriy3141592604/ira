package sql;

import java.util.LinkedList;
import java.util.List;

import sql.SqlLexerTest.TokenType;

public class SqlLexer {

	public List<Token> parse(String sourceInput) {
		final LinkedList<Token> tokens = new LinkedList<>();
		final char[] in = sourceInput.toCharArray();
		for (int i = 0; i < in.length;) {
			// TODO В этом месте пропадают пробелы в строках
			if (Character.isWhitespace(in[i])) {
				++i;
				continue;
			}
			{
				final String keywordTemplate = ",";
				if (isKeyword(i, in, keywordTemplate)) {
					tokens.add(new Token(TokenType.COMMA, keywordTemplate));
					i += keywordTemplate.length();
					continue;
				}
			}
			{
				final String keywordTemplate = ".";
				if (isKeyword(i, in, keywordTemplate)) {
					tokens.add(new Token(TokenType.DOT, keywordTemplate));
					i += keywordTemplate.length();
					continue;
				}
			}
			{
				final String keywordTemplate = "as";
				if (isKeyword(i, in, keywordTemplate)) {
					tokens.add(new Token(TokenType.AS, keywordTemplate));
					i += keywordTemplate.length();
					continue;
				}
			}

			{
				final String keywordTemplate = "select";
				if (isKeyword(i, in, keywordTemplate)) {
					tokens.add(new Token(TokenType.SELECT, keywordTemplate));
					i += keywordTemplate.length();
					continue;
				}
			}

			/** Должно быть последней проверкой **/
			{
				final StringBuilder tokenBuilder = new StringBuilder();
				while (i < in.length && Character.isAlphabetic(in[i])) {
					tokenBuilder.append(in[i]);
					i++;
				}
				/**
				 * FIXME: Добавлять, тольк если есть прочитанные символы
				 **/
				tokens.add(new Token(TokenType.IDENTIFIER, tokenBuilder.toString()));
				continue;
			}
		}
		return tokens;
	}

	private boolean isKeyword(int position, char[] in, String keywordTemplate) {
		if (position + keywordTemplate.length() > in.length) {
			return false;
		}
		if (in.length - position < keywordTemplate.length()) {
			return false;
		}
		return new String(in, position, keywordTemplate.length()).equals(keywordTemplate);
	}
}