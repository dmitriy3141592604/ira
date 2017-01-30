package utils.io;

import static utils.SideEffect.withEffect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import utils.ExceptionSupplier;

public class OnFileReader {

	private final ExceptionSupplier<Reader> readerSource;

	public OnFileReader(final File file) {
		this(withEffect((ExceptionSupplier<Reader>) (() -> new FileReader(file)), () -> {
			if (file == null) {
				throw new IllegalArgumentException("File can't be null");
			}
		}));
	}

	public OnFileReader(ExceptionSupplier<Reader> readerSource) {
		if (readerSource == null) {
			throw new IllegalArgumentException("Reader source cannot be null");
		}
		this.readerSource = readerSource;
	}

	public void accept(ExceptionConsumer<BufferedReader> f) {
		try (BufferedReader out = new BufferedReader(readerSource.get())) {
			f.safe(out);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

}
