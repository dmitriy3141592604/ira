package utils.io;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class TempTest {

	@Test
	public void testNotExistedFileAllowed() {
		final OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile_.txt"));
	}

	@Test
	@Ignore
	public void test2() {
		final OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile.txt"));
		final StringBuilder stringBuilder = new StringBuilder();
		reader.accept(t -> {
			stringBuilder.append(t.readLine());
		});
		assertEquals("1", stringBuilder.toString());
	}
}
