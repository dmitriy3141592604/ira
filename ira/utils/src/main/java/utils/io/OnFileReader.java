package utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import utils.ExceptionSupplier;

public class OnFileReader {

	private ExceptionSupplier<Reader> readerSource;

	public OnFileReader(File file) {
		this(() -> new FileReader(file));
	}

	public OnFileReader(ExceptionSupplier<Reader> readerSource) {
		if (readerSource == null) {
			throw new IllegalArgumentException("Reader source cannot be null");
		}
		this.readerSource = readerSource;
	}

	public void accept(ExceptionConsumer<BufferedReader> f) {
		try (BufferedReader out = new BufferedReader(readerSource.safe())) {
			f.safe(out);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
