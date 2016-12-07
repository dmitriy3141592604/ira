package uiserializer.bus;

import static org.junit.Assert.assertEquals;
import static uiserializer.bus.NaronEngine.newJSIntance;

import java.io.FileReader;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

public class BusTest {

	private final ScriptEngine engine = newJSIntance();

	@Before
	public final void setUpBusTest() throws Exception {
		try (Reader reader = new FileReader("scripts.js")) {
			engine.eval(reader);
		}
	}

	@Test
	public void test$001() throws Exception {
		final StringBuilder script = new StringBuilder();

		script.append("var marker = undefined;");
		script.append("bus.subscribe('theme1', function(e){ marker = e ; });");
		script.append("bus.publish('theme1','someValue');");
		script.append("marker;");
		assertEquals("someValue", engine.eval(script.toString()));
	}

	@Test(expected = ScriptException.class)
	public void test$002() throws Exception {
		final StringBuilder script = new StringBuilder();
		script.append("marker;");
		engine.eval(script.toString());
	}

}
