package tools;

import static org.junit.Assert.assertEquals;
import static utils.Quietly.quietly;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class StartServerTest {

	private static final int SERVER_PORT = 3333 + new Random().nextInt(10);
	private Server server;

	@Before
	public final void setUpStartServerTest() throws Exception {
		server = new Server(SERVER_PORT);
		server.setHandler(new AbstractHandler() {

			@Override
			public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
				quietly(() -> {
					response.setContentType("text/html; charset=utf-8");
					response.setStatus(HttpServletResponse.SC_OK);
					baseRequest.setHandled(true);
					response.getWriter().write("СоДерЖимООЕ");
				});

			}
		});
		server.start();
	}

	@After
	public final void tearDownStartServerTest() throws Exception {
		server.stop();
	}

	@Test
	public void test() throws Exception {

		final URL url = newURL();
		final InputStream openStream = url.openStream();
		final String content = IOUtils.toString((openStream), "utf-8");

		assertEquals("СоДерЖимООЕ", content);
	}

	@Test
	public void htmlUnitAccessTest() throws Exception {
		try (final WebClient wc = new WebClient();) {
			final HtmlPage page = wc.getPage(newURL());
			assertEquals("СоДерЖимООЕ", page.asText());
		}

	}

	private URL newURL() throws MalformedURLException {
		final String protocol = "http";
		final String host = "localhost";
		final int port = SERVER_PORT;
		final String file = "/health";
		return new URL(protocol, host, port, file);
	}

}
