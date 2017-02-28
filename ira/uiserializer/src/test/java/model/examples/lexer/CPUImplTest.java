package model.examples.lexer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CPUImplTest {

	public CPU cpu;

	@Before
	public final void CPUImplTestBase() {
		this.cpu = new CPUImpl();
	}

	@Test
	public void test$vanilaCase() {
		cpu.lineFeed();
		cpu.push('a');
		cpu.push('b');
		final Token token = cpu.pop();

		assertEquals("Token{*inline*[1:1]}", token.toString());
	}

	@Test
	public void test$twoTokensRemembering() {
		cpu.lineFeed();
		cpu.push('x');
		cpu.push('y');
		cpu.pop();
		cpu.push('u');
		cpu.push('v');

		assertEquals("Token{*inline*[1:3]}", cpu.pop().toString());
	}

	@Test
	public void test$lastPushedCharIsAccessible() {
		cpu.push('b');
		assertEquals('b', cpu.getCurrentChar());
	}

	// TODO Множественное eof не должно приводить к созданию пустых строк
	@Test
	public void test$eofProcessing() {
		cpu.push('u');
		cpu.eof();
		assertEquals("u", cpu.pop().getValue());
	}

	@Test
	public void test$eofProcessing$multiplyCalls() {
		cpu.push('u');
		cpu.eof();
		cpu.eof();
		assertEquals("u", cpu.pop().getValue());
		assertEquals(false, cpu.hasNextToken());
	}

	@Test
	public void test$hasNextToken$noToken() {
		assertEquals(false, cpu.hasNextToken());
	}

	@Test
	public void test$hasNextToken$oneTokenExists() {
		cpu.push('t');
		cpu.pop();
		assertEquals(false, cpu.hasNextToken());
	}

	@Test
	public void test$newInputFileProcessingAction() {
		cpu.lineFeed();
		cpu.push('x');
		cpu.push('y');
		cpu.newSource("newSource");
		cpu.lineFeed();
		cpu.push('a');
		cpu.push('b');
		cpu.eof();
		assertEquals("Token{*inline*[1:1]}", cpu.pop().toString());
		assertEquals("Token{newSource[1:1]}", cpu.pop().toString());
	}

	// @Test
	// public void test$multiplePop(){
	// cpu.lineFeed();
	// cpu.push('x');
	// cpu.pop();
	// cpu.pop();
	//// assertEquals("Token{*iniline*[1:1]}", cpu.pot)
	// }

}
