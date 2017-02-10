package sql;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

// FIXED: LEVEL 1
// select st.login as login, st.city as city
// TODO: LEVEL 2
// select st.login as login, st.city as city from other_table as st
public class SqlLexerTest {

	private SqlLexer sqlLexer;

	public enum TokenType {
		SELECT, IDENTIFIER, DOT, COMMA, AS;
	}

	@Before
	public void setUpSqlLexerTestBase() {
		sqlLexer = new SqlLexer();
	}

	@Test
	public void test$emptyInput() {
		assertEquals("[]", parse("").toString());
	}

	@Test
	public void test$emptyInputWithSpaces() {
		assertEquals("[]", parse(" ").toString());
	}

	@Test
	public void test$selectKeyword() {
		assertEquals("[{SELECT:select}]", parse("select").toString());
	}

	@Test
	public void test$selectKeyword$2() {
		assertEquals("[{SELECT:select}]", parse("select ").toString());
	}

	@Test
	public void test$identifier() {
		assertEquals("[{IDENTIFIER:login}]", parse("login").toString());
	}

	@Test
	public void test$dot() {
		assertEquals("[{DOT:.}]", parse(".").toString());
	}

	@Test
	public void test$coma() {
		assertEquals("[{COMMA:,}]", parse(",").toString());
	}

	@Test
	public void test$as() {
		assertEquals("[{AS:as}]", parse("as").toString());
	}

	@Test
	// @Ignore
	public void test$level1() {
		final StringBuilder sb = new StringBuilder();
		sb.append("[{SELECT:select},");
		sb.append(" {IDENTIFIER:st},");
		sb.append(" {DOT:.},");
		sb.append(" {IDENTIFIER:login},");
		sb.append(" {AS:as},");
		sb.append(" {IDENTIFIER:login},");
		sb.append(" {COMMA:,},");
		sb.append(" {IDENTIFIER:st},");
		sb.append(" {DOT:.},");
		sb.append(" {IDENTIFIER:city},");
		sb.append(" {AS:as},");
		sb.append(" {IDENTIFIER:city}]");
		assertEquals(sb.toString(), parse("select st.login as login, st.city as city").toString());
	}

	public List<Token> parse(String sourceInput) {
		return sqlLexer.parse(sourceInput);
	}

}
