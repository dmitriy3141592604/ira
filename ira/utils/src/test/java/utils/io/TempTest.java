package utils.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.junit.Test;

public class TempTest {

	@Test
	public void testNotExistedFileAllowed() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile_.txt"));
	}

	@Test
	public void testFileReading() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile.txt"));
		StringBuilder stringBuilder = new StringBuilder();
		reader.accept(t -> {
			stringBuilder.append(t.readLine());
		});
		assertEquals("1", stringBuilder.toString());
	}

	@Test
	public void testNotExistingWriteFileAllowed() {
		new OnFileWriter(new File("c:\\tmp\\somefile_.txt"));
	}

	@Test
	public void testFileWriting() {
		OnFileWriter onFileWriter = new OnFileWriter(new File("c:\\tmp\\writefile.txt"));
		onFileWriter.accept(new ExceptionConsumer<PrintWriter>() {

			@Override
			public void accept(PrintWriter t) throws Exception {
				t.println("some text");
			}
		});

	}

	@Test
	public void testExceptionSupplierWritingWith() {
		OnFileWriter onFileWriter = new OnFileWriter(() -> new FileWriter(new File("c:\\tmp\\writees.txt")));

		onFileWriter.accept(t -> t.println("some other text"));

	}
}
