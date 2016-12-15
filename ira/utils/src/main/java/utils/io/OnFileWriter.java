package utils.io;

import static utils.Safer.safe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;

import utils.Responsibility;

/**
 * Предоставляет интерфейс для возможности записи в файл или Writer
 * без необходимости объявлять функцию с throws IOException или ловить IOException.
 *
 */
@Responsibility("Скрывает особенности жизненного цикла файлого потока на запись. В том числе исключительные ситуации")
public class OnFileWriter {

	public static void onFileWriter(File file, ExceptionConsumer<PrintWriter> f) {
		new OnFileWriter(file).accept(f);
	}

	private final Writer writer;

	public OnFileWriter(Writer writer) {
		this.writer = writer;
	}

	public OnFileWriter(File exchangePoint) {
		this.writer = safe(() -> new FileWriter(exchangePoint));
	}

	// beforeAccept 
	public final void accept(ExceptionConsumer<PrintWriter> f) {
		try (final PrintWriter out = wrap()) {
			f.safe(out);
		}
	}

	private PrintWriter wrap() {
		return new PrintWriter(new BufferedWriter(writer));
	}

}
