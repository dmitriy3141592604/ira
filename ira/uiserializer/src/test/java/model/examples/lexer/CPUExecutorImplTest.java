package model.examples.lexer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import utils.Behavior;

public class CPUExecutorImplTest extends CPUExecutorImplTestBase {

	@Test
	@Behavior("При чтении входного потока: 1) задается нова строка, 2) для всех символов pus, 3) в конце: eof")
	public void test$parseSimpleLine() throws IOException {
		process("hello");
		assertEquals("[lineFeed, push, push, push, push, push, pop, eof]", resultLog());
	}

	@Test
	@Behavior("Переводы строк во входном потоке вызыват lineFeed")
	public void test$innerLineFeeds() {
		process("ab\ncd");
		assertEquals("[lineFeed, push, push, lineFeed, push, push, pop, eof]", resultLog());
	}

	@Test
	@Behavior("Специфика теста: пробел вызывает pop")
	public void test$popWhenSpace() {
		process("a c");
		assertEquals("[lineFeed, push, pop, push, pop, eof]", resultLog());
		final StringBuilder str = new StringBuilder();
		parsedTokens.forEach(t -> str.append(t.getValue()).append(";"));
		assertEquals("a;c;", str.toString());
	}

}
