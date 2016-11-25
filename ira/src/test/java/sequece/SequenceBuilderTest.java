package sequece;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import utils.IraTest;

class ObjectInstance {

	private List<Exchange> exchanges;

	private String name;

	private String role;

	public ObjectInstance(String name) {
		this.name = name;
	}

	public ObjectInstance send(String message, ObjectInstance target) {
		return target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Exchange> getExchanges() {
		return exchanges;
	}

	public void setExchanges(List<Exchange> exchanges) {
		this.exchanges = exchanges;
	}

	public void returns(String string, ObjectInstanceWithDefaultTarget aalisa) {
		// TODO Auto-generated method stub

	}

}

class ObjectInstanceWithDefaultTarget extends ObjectInstance {
	public ObjectInstanceWithDefaultTarget(String name, ObjectInstance defaultTarget) {
		super(name);
		this.defaultTarget = defaultTarget;
	}

	private final ObjectInstance defaultTarget;

	public ObjectInstance send(String message) {
		return send(message, defaultTarget);
	}

}

class Exchange {

	private final String name;

	private final ObjectInstance receiver;

	private final ObjectInstance sender;

	public String getName() {
		return name;
	}

	public ObjectInstance getReceiver() {
		return receiver;
	}

	public ObjectInstance getSender() {
		return sender;
	}

	public Exchange(ObjectInstance sender, String message, ObjectInstance receiver) {
		this.receiver = receiver;
		this.sender = sender;
		this.name = message;
	}

}

class SequenceDiagramModel {
	static class RowModel {
		String sender;
		String message;
		String receiver;

		public RowModel(String sender, String message, String receiver) {
			this.sender = sender;
			this.message = message;
			this.receiver = receiver;
		}

		static RowModel newRowModel(String sender, String message, String receiver) {
			return new RowModel(sender, message, receiver);
		}

	}

	private final List<String> objects = new ArrayList<String>();
	private final List<RowModel> arrows = new ArrayList<RowModel>();

	public void addObject(String name) {
		objects.add(name);
	}

	void exchange(String sender, String message, String receiver) {
		arrows.add(RowModel.newRowModel(sender, message, receiver));
	}

	public BufferedImage generate() {
		final BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g = bufferedImage.createGraphics();

		build(g);

		return bufferedImage;

	}

	private void build(Graphics2D g) {
		final Font font = new Font("Serif", Font.PLAIN, 30);
		g.setFont(font);

		final String str = "Здравствуй, Мир!";
		final FontMetrics metric = g.getFontMetrics();
		final int width = metric.stringWidth(str);
		final int height = metric.getHeight();
		g.setColor(Color.black);

		final int x = 150;
		final int y = 80;

		g.drawString(str, x, y);
		g.drawRect(x - 5, y + 10 - height, width + 10, height - 2);
	}
}

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
		alisa.send("Нажимает отправить"); // Возврящаться должна связка, что бы
											// запоминать, кто отправил
											// сообщение
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
