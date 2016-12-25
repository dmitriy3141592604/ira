package uiserializer.components.write.bootstrap.buttons;

import java.io.File;

import org.junit.Test;

import utils.io.OnFileWriter;

public class ButtonsTest extends BootstrapButtonsTestBase<BootstrapButtonComponent> {

	@Override
	protected BootstrapButtonComponent newComponent() {
		return new BootstrapButtonComponent();
	}

	@Test
	public void test$ButtonTags() {
		final File exchangePoint = new File("target\\buttonTest.html");
		final OnFileWriter onFileWriter = new OnFileWriter(exchangePoint);
		onFileWriter.accept(out -> {
			out.write(serializedContent());
		});
	}

}
