package uiserializer.font;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class TerminalFontBuilder {

	public static void main(String... args) throws Exception {
		final Server server = startServer();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (this) {
					try {
						System.out.println("server started");
						if (" ".isEmpty()) {
							wait(10000);
							server.stop();
							System.out.println("server stopped by development time out");
						}
					} catch (final InterruptedException e) {
						return;
					} catch (final Exception e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

		server.join();
	}

	private static Server startServer() throws Exception {
		final Server server = new Server(3030);
		server.setHandler(new FontBuilderHandler());
		server.start();
		return server;
	}
}

class FontBuilderHandler extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);

			System.out.println("target: " + target);
			if ("/font".equals(target)) {
				processResource(target, baseRequest, response, "font", "text");
				baseRequest.setHandled(true);
				return;
			}

			if ("/edit.html".equals(target)) {
				processResource(target, baseRequest, response, "edit.html", "html");
				baseRequest.setHandled(true);
				return;
			}
			if ("/stop".equals(target)) {
				System.out.println("exit");
				System.exit(0);
				baseRequest.setHandled(true);
				return; // :)
			}
			if ("/getStatus".equals(target) || "/flip".equals(target)) {

				final ServletInputStream inputStream = request.getInputStream();

				if ("/getStatus".equals(target)) {
					println("GetStatus");

					final String c = request.getParameter("c");
					final Integer x = Integer.valueOf(request.getParameter("x"));
					final Integer y = Integer.valueOf(request.getParameter("y"));
					final Map<String, List<String>> fontMap = readFontMap();
					// response.getWriter().println("{ \"status\":\"" + fontMap.get(c).get(y).charAt(x) + "\"}");
					response.getWriter().println(fontMap.get(c).get(y).charAt(x));
					baseRequest.setHandled(true);
					return;
				}

				if ("/flip".equals(target)) {
					final StringWriter argsString = new StringWriter();
					IOUtils.copy(inputStream, argsString);
					System.out.println(request);

					final String[] split = argsString.toString().split("&");
					final Integer x = Integer.valueOf(split[0].substring(2));
					final Integer y = Integer.valueOf(split[1].substring(2));
					final String ch = split[2].substring(2);
					final String x2 = "Change: " + ch + " " + "x = " + x + " y = " + y;
					println(x2);

					baseRequest.setHandled(true);

					final Map<String, List<String>> fontMap = readFontMap();

					List<String> list = fontMap.get(ch);
					if (list == null) {
						println("No predefined table for symbol: " + ch + " reinitialize");
						fontMap.put(ch, list = new ArrayList<String>());
						while (list.size() < 8) {
							list.add("        ");
						}
					} else {
						println("Use existing table for symbol " + ch);
					}

					println("Transform SOURCE is " + list);
					final char[] charArray = list.get(y).toCharArray();
					if (charArray[x] == ' ') {
						charArray[x] = '+';
					} else {
						charArray[x] = ' ';
					}
					list.set(y, String.valueOf(charArray));

					println("Transform RESULT is " + list);

					try (PrintWriter out = new PrintWriter(new FileWriter(new File("terminal.font")))) {
						for (final String outSymbol : fontMap.keySet()) {
							println("Start table for: " + outSymbol);
							out.println(outSymbol);
							for (final String line : fontMap.get(outSymbol)) {
								out.println(line);
							}
						}

					}

					return;
				}
			}
			response.setContentType("text/html; charset=utf-8");
			response.setStatus(HttpServletResponse.SC_OK);

			final PrintWriter out = response.getWriter();

			out.println("<h1>" + "Привет" + "</h1>");

			baseRequest.setHandled(true);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}

	}

	private Map<String, List<String>> readFontMap() throws IOException, FileNotFoundException {
		final Map<String, List<String>> fontMap = new TreeMap<String, List<String>>();

		try (BufferedReader reader = new BufferedReader(new FileReader("terminal.font"))) {
			String symbol = null;
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				if (line.length() == 1) {
					symbol = line;
					println("Readed start table of " + symbol);
					continue;
				} else {
					List<String> list = fontMap.get(symbol);
					if (list == null) {
						fontMap.put(symbol, list = new ArrayList<String>());
					}
					list.add(line);
				}
			}
		}
		return fontMap;
	}

	private void println(String x2) {
		System.out.println(x2);
	}

	private void processResource(String target, Request baseRequest, HttpServletResponse response, String resourceName, String string)
			throws IOException {
		notifyProcessStarted(target);
		response.setContentType("text/" + string + "; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		final InputStream resource = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		try {
			IOUtils.copy(resource, response.getWriter());
			baseRequest.setHandled(true);
		} catch (final Exception e) {
			throw new RuntimeException("Error by processing target: " + target, e);
		}
		return;
	}

	private void notifyProcessStarted(String target) {
		System.out.println("process" + target);
	}

}
