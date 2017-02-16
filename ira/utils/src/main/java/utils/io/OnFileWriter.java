package utils.io;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Path;

import utils.ExceptionSupplier;
import utils.Responsibility;

/**
 * Предоставляет интерфейс для возможности записи в файл или Writer без необходимости объявлять функцию с throws IOException или ловить IOException.
 *
 */
// TODO (tdv): Придумать как бросать исключение при передаче нереального
// TODO (tdv): Придумать как бросать исключение при передаче null
@Responsibility("Скрывает особенности жизненного цикла файлого потока на запись. В том числе исключительные ситуации")
public class OnFileWriter {

	/**
	 * Выводит строковое представление <code>content</code> в файл с именем <code>fileName</code>
	 *
	 * @param fileName
	 *            Имя файла, в который будет записано значение <code>content</code>
	 * @param content
	 *            Содержимое файла. Используется Object.toString для получения сериализованного представления
	 */
	public static void dumpToFile(String fileName, Object content) {
		onFileWriter(fileName, pw -> pw.println(content));
	}

	public static void onFileWriter(String fileName, ExceptionConsumer<PrintWriter> f) {
		onFileWriter(new File(fileName), f);
	}

	public static void onFileWriter(File file, ExceptionConsumer<PrintWriter> f) {
		new OnFileWriter(file).accept(f);
	}

	private final ExceptionSupplier<Writer> writerSource;

	public OnFileWriter(ExceptionSupplier<Writer> writerSource) {
		if (writerSource == null) {
			throw new IllegalArgumentException("Writer source cannot be null");
		}
		this.writerSource = writerSource;
	}

	public OnFileWriter(File file) {
		this(new ExceptionSupplier<Writer>() {

			@Override
			public Writer create() throws Exception {
				return new FileWriter(file);
			}
		});
	}

	public OnFileWriter(Path path) {
		this(new ExceptionSupplier<Writer>() {

			@Override
			public Writer create() throws Exception {
				return new FileWriter(path.toFile());
			}
		});
	}

	public final void accept(ExceptionConsumer<PrintWriter> f) {
		try (final PrintWriter out = new PrintWriter(writerSource.get())) {
			f.safe(out);
		}
	}

}
