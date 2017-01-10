package uiserializer;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import application.support.WithName;
import utils.Responsibility;
import utils.io.OnFileWriter;

// TODO Можно добавить декоратор, для гибкости
@Responsibility("Стратегия получения выходного файлового потока на основе имени класса объекта")
public class ApplicationSerializerConfiguration {

	private final OnFileWriter fileWriter;

	// XXX Возможно потребуется переименование 2017.01.10
	public ApplicationSerializerConfiguration(WithName named) {
		// TODO Добавить getDefaultFileSystem#getPath
		final Path path = FileSystems.getDefault().getPath("target", named.name() + "." + "html");
		this.fileWriter = new OnFileWriter(path);
	}

	public OnFileWriter getWriter() {
		return fileWriter;
	}

}