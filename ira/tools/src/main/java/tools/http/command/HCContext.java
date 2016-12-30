package tools.http.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;

import utils.Responsibility;

@Responsibility("Предоставляет интерфейс доступа к контексту обработки HTTP-запроса для Jetty")
public interface HCContext {

	String target();

	Request baseRequest();

	HttpServletRequest servletRequest();

	HttpServletResponse servletResponse();

}
