package tools.http.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import uiserializer.Mockitor;

@RunWith(MockitoJUnitRunner.class)
public abstract class HttpCommandTestBase implements Mockitor {

	@Mock
	protected HttpServletResponse response;

	protected HttpCommandContext context;

	@Before
	public final void setUpHttpCommandTestBase() {
		context = new HttpCommandContext() {

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
				throw new UnsupportedOperationException();
			}
		};
	}

}