package userstories;

import static utils.io.OnFileWriter.onFileWriter;

import java.io.File;

import userstories.actions.Action;
import userstories.actions.ActionDescription;
import utils.Triple;

public class SpecWriter {

	public static void main(String[] args) throws Exception {
		final UserCaseVanilaSpec spec = new UserCaseVanilaSpec();
		spec.execute();

		dumpSpecToConsole(spec);

		dumpSeleniumTest(spec, "c:\\wks\\tmp\\tmpPage\\tests\\" + UserCaseVanilaSpec.class.getSimpleName() + ".html");
	}

	private static void dumpSeleniumTest(UserCaseVanilaSpec spec, String setleniumTestCaseFileName) {
		onFileWriter(new File(setleniumTestCaseFileName), out -> {
			final String head = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n" + "<body>\r\n"
					+ "<table cellpadding=\"1\" cellspacing=\"1\" border=\"1\">\r\n" + "<thead>\r\n"
					+ "<tr><td rowspan=\"1\" colspan=\"3\">test_case1</td></tr>\r\n" + "</thead><tbody>";

			final String footer = "</tbody></table>\r\n" + "</body>\r\n" + "</html>";

			out.println(head);
			for (final Action executedAction : spec.getExecutedActions()) {
				final Triple<String, String, String> command = executedAction.getSeleniumCode();
				final StringBuilder sb = new StringBuilder();
				sb.append("<tr><td>");
				sb.append(nullAsEmptyString(command.getFst()));
				sb.append("</td><td>");
				sb.append(nullAsEmptyString(command.getSnd()));
				sb.append("</td><td>");
				sb.append(nullAsEmptyString(command.getThird()));
				sb.append("</td></tr>");
				out.println(sb.toString());
			}
			out.println(footer);
		});

	}

	private static Object nullAsEmptyString(String third) {
		if (third == null) {
			return "";
		}
		return third;
	}

	private static void dumpSpecToConsole(final UserCaseVanilaSpec spec) {
		final String specDescription = spec.getClass().getAnnotation(SpecDescription.class).value();
		System.out.println("");
		System.out.println(specDescription.toUpperCase());
		System.out.println("--------------------------------");

		for (final Action executed : spec.getExecutedActions()) {
			final String description = executed.getClass().getAnnotation(ActionDescription.class).value();
			System.out.println(description);
		}
	}
}
