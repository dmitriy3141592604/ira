package sequece;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import utils.IraTest;

public class SequenceBuilderTest extends IraTest {

	@Rule
	public TestName testName = new TestName();

	private ObjectInstanceWithDefaultTarget alisa;

	private ObjectInstance page;

	private ArrayList<Exchange> exchanges;

	private SequenceDiagramModel model;

	@Before
	public void setUp() {
		exchanges = new ArrayList<Exchange>();
		page = new ObjectInstance("Page");
		alisa = new ObjectInstanceWithDefaultTarget("Alisa", page);
		model = new SequenceDiagramModel();
	}

	@Test
	public void test() throws Exception {
		alisa.send("Водит логин");
		alisa.send("Вводит пароль");
		alisa.send("Нажимает отправить");
		page.returns("Wellcome page", alisa);

		model.addObject(alisa.getName());
		model.addObject(page.getName());

		exchanges.forEach(this::addToModel);

		final BufferedImage g = model.generate();

		final String simpleImageFileName = "sequenceDiagram" + testName.getMethodName() + ".png";
		final String imageFileName = "spec" + File.separator + simpleImageFileName;
		ImageIO.write(g, "PNG", new File(imageFileName));
		try (PrintStream out = new PrintStream(new FileOutputStream(imageFileName.replaceAll(".[^.]+$", ".html")))) {
			out.print(("<img src='" + simpleImageFileName + "'/>"));
		}

	}

	void addToModel(Exchange exchanges) {
		model.exchange(exchanges.getSender().getName(), exchanges.getName(), exchanges.getReceiver().getName());
	}

}
