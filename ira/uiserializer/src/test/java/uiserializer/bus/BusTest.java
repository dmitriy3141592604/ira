package uiserializer.bus;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BusTest {

	private static String scriptsSource;

	private ScriptEngine engine;

	@BeforeClass
	public static void setUpBusTestClass() throws Exception {
		final InputStream is = new FileInputStream("scripts.js");

		final String string = IOUtils.toString(is);

		scriptsSource = string;
	}

	@Before
	public final void setUpBusTest() throws Exception {
		final ScriptEngineManager factory = new ScriptEngineManager();
		engine = factory.getEngineByName("nashorn");
		engine.eval(scriptsSource);
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

}
