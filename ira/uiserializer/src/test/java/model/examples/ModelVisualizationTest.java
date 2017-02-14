package model.examples;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ModelVisualizationTest {

	@Test
	public void test$modelVisualization() throws Exception {
		ModelVisualization.setCreateFileSystemArtifacts(false);
		final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		final PrintStream ps = new PrintStream(outputContent);
		ModelVisualization.setOutput(ps);

		ModelVisualization.main("");
		ps.close();

		final byte[] byteArray = outputContent.toByteArray();
		byte b = 0;
		for (int i = 0; i < byteArray.length; ++i) {
			b += byteArray[i];
		}
		assertEquals((byte) 25, b);
	}

}
