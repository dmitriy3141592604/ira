package uiserializer.bus;

import java.util.function.Supplier;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class NaronEngine {

	private static ScriptEngineManager factory = new ScriptEngineManager();

	private static ScriptEngine nashorn = new Supplier<ScriptEngine>() {

		@Override
		public ScriptEngine get() {

			return factory.getEngineByName("nashorn");
		}

	}.get();

	public static ScriptEngine newJSIntance() {
		nashorn.setBindings(nashorn.createBindings(), ScriptContext.ENGINE_SCOPE);
		return nashorn;
	}

}
