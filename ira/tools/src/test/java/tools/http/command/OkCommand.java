package tools.http.command;

import static javax.servlet.http.HttpServletResponse.SC_OK;

import utils.Responsibility;

@Responsibility("Устанавливает 200 статус ответа Http-сервера")
public class OkCommand extends HttpCommandBase {

	@Override
	public void apply(HttpCommandContext context) {
		context.servletResponse().setStatus(SC_OK);
	}

}
