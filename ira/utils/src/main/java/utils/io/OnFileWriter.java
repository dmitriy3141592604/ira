package utils.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Предоставляет интерфейс для возможности записи в файл или Writer
 * без необходимости объявлять функцию с throws IOException или ловить IOException.
 *
 */
public class OnFileWriter {

	public static void onFileWriter(File file, IOConsumer<PrintWriter> f) {
		new OnFileWriter(file).accept(f);
	}

	private final Writer writer;

	public OnFileWriter(Writer writer) {
		this.writer = writer;
	}

	public OnFileWriter(File exchangePoint) {
		try {
			this.writer = new FileWriter(exchangePoint);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void accept(IOConsumer<PrintWriter> f) {
		try (final PrintWriter out = new PrintWriter(new BufferedWriter(writer));) {
			f.save(out);
		}
	}

}
