package utils.io;

import static utils.Safer.safe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OnFileReader {

	private final BufferedReader reader;

	public OnFileReader(File file) {
		this.reader = safe(() -> new BufferedReader(new FileReader(file)));
	}

	public void accept(ExceptionConsumer<BufferedReader> f) {
		try (BufferedReader out = reader) {
			f.safe(out);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
