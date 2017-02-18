package model.examples.lexer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Function;

import org.apache.commons.codec.digest.DigestUtils;

import model.Node;
import utils.Responsibility;

/**
 * Класс упрощает создание dot файлов.
 *
 * Никаких особых функций он не предоставляет, скорее похож на набора макросов.
 *
 * Пользователь должен понимать, что и когда вызывать. Для этого нужно читать код.
 *
 *
 */
// FIXME !Not Tested
public class Dot {

	@Responsibility("Отвечает за хранение уровня вложенности")
	private int indentLevel = 0;

	@Responsibility("Содержит результ")
	private final StringWriter sw = new StringWriter();

	@Responsibility("Обертка, для простоты формирования результата")
	private final PrintWriter pw = new PrintWriter(sw);

	@Responsibility("Подписывает результат. Если подпись не пустая, то выводится комментарием в конце файла")
	private final Function<String, String> crcHandler = (source) -> {
		final String md5Hex = DigestUtils.md5Hex(source);
		return md5Hex;
	};

	@Override
	@Responsibility("Предоставляет доступ к результату")
	public String toString() {
		final String retVal = sw.toString();
		final String hex = crcHandler.apply(retVal);
		if (hex.isEmpty()) {
			return retVal;
		}
		return retVal + "\n" + "# " + hex;
	}

	@Responsibility("Входая точка для формирования результата")
	public Dot newGraph(String name) {
		pw.append("digraph").append(" ").append(name).append(" ").append("{").println();
		indentLevel++;
		return this;
	}

	@Responsibility("Фиксирует результат")
	public void complete() {
		indentLevel--;
		pw.println("}");

	}

	@Responsibility("Добавляет смещение для последующего вывода")
	public Dot indent() {
		final String indent = "\t";
		for (int i = 0; i < indentLevel; ++i) {
			pw.print(indent);
		}
		return this;
	}

	@Responsibility("Добавляет окончание строки. Вместе с символом ';'")
	public Dot eol() {
		pw.println(";");
		return this;
	}

	@Responsibility("Отрисовывает узел графа. Возвращает средство конфигурирования. Минимальная конфигурация - это сигнал окончания конфигурирования")
	public NodeConfig node(Node node) {
		indent();
		pw.append(node.name());
		// FIXME Нужна обработка факта, конфигурация будет закрыта
		return new NodeConfig();
	}

	@Responsibility("Предоставляет средства дял конфигурирования узла")

	public class NodeConfig {

		@Responsibility("Фиксирует конфигурирование узла, возвращает цепочку вызовов к основному графу")
		public Dot configured() {
			eol();
			return Dot.this;
		}
	}

	@Responsibility("Средство создание ребра графа. Возвращает средство конфигурирования. Минимальная конфигурация - сигнал окончания конфигурирования")
	public EdgeConfig transit(Node sourceNode, Node targetNode) {
		indent();
		pw.append(sourceNode.name()).append("->").append(targetNode.name());
		return new EdgeConfig();
	}

	@Responsibility("Предоставляет средства для конфигурирования ребра графаф")
	public class EdgeConfig {

		@Responsibility("Фиксирует конфигурирования ребра, возвращает цепочку вызовов к основному графу")
		public Dot configured() {
			// FIXME Нужна обработка факта, конфигурация будет закрыта
			eol();
			return Dot.this;
		}

	}
}