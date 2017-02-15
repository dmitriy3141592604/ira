package model.examples;

import static java.lang.System.exit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import model.Edge;
import model.Node;

public class LoginFormExample implements Supplier<Integer> {

	public interface Empty {

	}

	public interface Form {

		Empty login();

		Empty password();

		Empty submit();

		Empty ts();
	}

	public static class MethodCallRecorder {

		public <T> T recordMethodCallFor(Class<T> t, Node node) {
			return impl(t, node);
		}

		@SuppressWarnings("unchecked")
		private <T> T impl(Class<T> t, Node node) {
			return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class<?>[] { t }, new InvocationHandler() {

				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
					node.bindedWith(new Node(null), method.getName());
					return impl(method.getReturnType(), node);
				}
			});
		}

	}

	@Override
	public Integer get() {

		final Node node = new Node((String) null);

		final Form form = new MethodCallRecorder().recordMethodCallFor(Form.class, node);

		{
			form.ts();
			form.login();
			form.password();
			form.submit();
		}

		final StringWriter sOut = new StringWriter();
		final PrintWriter out = new PrintWriter(sOut);
		out.println("<form>");
		for (final Edge edge : node.edges()) {
			out.append("<input name='").append(edge.name()).append("'/>").println();
		}
		out.println("</form>");

		System.out.println(sOut.toString());

		return 0;
	}

	public static void main(String... args) {
		exit(new LoginFormExample().get());
	}

}
