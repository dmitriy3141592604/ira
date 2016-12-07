package uiserializer.bus;

import static org.junit.Assert.assertEquals;
import static uiserializer.bus.NaronEngine.newJSIntance;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.junit.Test;

public class NaronEngineTest {

	/**
	 * При повторном обращении к engine станые связи перестают существовать
	 */
	@Test(expected = ScriptException.class)
	public void test$recreate() throws Exception {
		newJSIntance().eval("var someValue = 1;");
		newJSIntance().eval("someValue");
	}

	/**
	 * В рамках одного engine значие живет от вызова к вызову
	 */
	@Test
	public void test$reuse() throws Exception {
		final ScriptEngine js = newJSIntance();
		js.eval("var someValue = 1");
		assertEquals("1", js.eval("someValue").toString());

	}
}
