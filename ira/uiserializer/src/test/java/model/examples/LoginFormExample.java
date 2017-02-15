package model.examples;

import static java.lang.System.exit;
import static java.lang.reflect.Proxy.newProxyInstance;
import static model.Node.newNode;

import java.io.PrintWriter;
import java.io.StringWriter;
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

		private final ClassLoader cl = getClass().getClassLoader();

		public <T> T recordMethodCallFor(Class<T> t, Node node) {
			return impl(t, node);
		}

		private <T> T impl(Class<T> t, Node node) {

			return t.cast(newProxyInstance(cl, new Class<?>[] { t }, (proxy, method, args) -> {
				node.bindedWith(newNode(null), method.getName());
				return impl(method.getReturnType(), node);
			}));
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

		System.out.println(sOut);

		return 0;
	}

	public static void main(String... args) {
		exit(new LoginFormExample().get());
	}

}
