package model.examples.loginForm;

import static java.lang.System.exit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;

import model.Edge;
import model.Node;

public class LoginFormExample implements Supplier<Integer> {

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
