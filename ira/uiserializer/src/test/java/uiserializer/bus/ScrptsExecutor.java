package uiserializer.bus;

import static org.junit.Assert.assertEquals;
import static uiserializer.bus.NaronEngine.newJSIntance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ScrptsExecutor {

	private final ScriptEngine js = newJSIntance();

	private final File left;

	private Reader source;

	@Parameters(name = "{0}")
	public static Iterable<File> data() throws Exception {
		return readRecursive(new File("scripts"), new ArrayList<File>());

	}

	/*TODO Не обрабатывает символьные ссылки. Нужны дополнительные проверки! */
	private static List<File> readRecursive(File root, List<File> acc) {
		final File[] listFiles = root.listFiles((dir, name) -> name.endsWith(".js"));
		for (final File file : listFiles) {
			if (file.isDirectory()) {
				readRecursive(file, acc);
			} else {
				acc.add(file);
			}
		}
		return acc;
	}

	public ScrptsExecutor(File lelft) {
		this.left = lelft;
	}

	@Before
	public void setUp() throws Exception {
		try (StringWriter libraries = new StringWriter()) {
			//System.out.println("out opened");
			try (BufferedReader preReader = new BufferedReader(new FileReader(left))) {
				String line = null;
				//System.out.println("before cycle");
				while (null != (line = preReader.readLine())) {
					if (line.matches("\"bus[.]js\"")) {
						//System.out.println("copied");
						final String s = "jslib/" + "bus.js";
						//System.out.println("script intput " + s);
						js.eval((new FileReader(s)));
					}
				}
			}
		}
		source = new FileReader(left);
	}

	@Test
	public void test() throws Exception {
		assertEquals("Ok", js.eval(source).toString());
	}

}
