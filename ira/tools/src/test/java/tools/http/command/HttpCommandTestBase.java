package tools.http.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import testutils.RandomizedTest;
import uiserializer.Mockitor;

@RunWith(MockitoJUnitRunner.class)
public abstract class HttpCommandTestBase implements Mockitor, RandomizedTest {

	@Mock
	protected HttpServletResponse response;

	@Mock
	protected Request request;

	@Mock
	protected PrintWriter responseWriter;

	protected String randomString;

	protected HCContext context;

	@Before
	public final void setUpHttpCommandTestBase() {
		randomString = randomString();
		try {
			when(response.getWriter()).thenReturn(responseWriter);
			context = new HCContext() {

				@Override
				public String target() {
					throw new UnsupportedOperationException();
				}

				@Override
				public HttpServletResponse servletResponse() {
					return response;
				}

				@Override
				public HttpServletRequest servletRequest() {
					throw new UnsupportedOperationException();
				}

				@Override
				public Request baseRequest() {
					return request;
				}
			};
		} catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}