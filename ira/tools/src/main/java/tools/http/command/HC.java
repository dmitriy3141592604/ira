package tools.http.command;

import java.util.function.Consumer;

import utils.Responsibility;

/**
 * HC == HtmlCommand
 */
@Responsibility("Фиксирует контракт комманды обработки обработчика запросов к Jetty")
public interface HC extends Consumer<HCContext> {

}
