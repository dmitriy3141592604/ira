package utils.io;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class TempTest {

	@Test
	public void testNotExistedFileAllowed() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile_.txt"));
	}

	@Test
	public void test2() {
		OnFileReader reader = new OnFileReader(new File("c:\\tmp\\somefile.txt"));
		StringBuilder stringBuilder = new StringBuilder();
		reader.accept(t -> {
			stringBuilder.append(t.readLine());
		});
		assertEquals("1", stringBuilder.toString());
	}
}
